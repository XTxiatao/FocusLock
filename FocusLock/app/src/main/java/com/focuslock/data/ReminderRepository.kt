package com.focuslock.data

import com.focuslock.model.Reminder
import com.focuslock.model.ReminderRecurrence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ReminderRepository(
    private val reminderDao: ReminderDao
) {

    val remindersFlow: Flow<List<Reminder>> = reminderDao.observeAll()
        .map { entities ->
            entities
                .map { it.toModel() }
                .sortedBy { it.sortKey() }
        }

    suspend fun addReminder(reminder: Reminder) {
        withContext(Dispatchers.IO) {
            reminderDao.insert(reminder.toEntity())
        }
    }

    suspend fun updateReminder(reminder: Reminder) {
        withContext(Dispatchers.IO) {
            reminderDao.update(reminder.toEntity())
        }
    }

    suspend fun deleteReminder(reminder: Reminder) {
        withContext(Dispatchers.IO) {
            reminderDao.delete(reminder.toEntity())
        }
    }

    suspend fun ensureUniqueTitle(title: String, reminderId: Long?): Boolean {
        return withContext(Dispatchers.IO) {
            if (reminderId == null) {
                reminderDao.countByTitle(title) == 0
            } else {
                reminderDao.countByTitleExcluding(title, reminderId) == 0
            }
        }
    }
}

private fun ReminderEntity.toModel(): Reminder {
    return Reminder(
        id = id,
        title = title,
        description = description.orEmpty(),
        anchorDateTimeMillis = anchorDateTime,
        recurrence = ReminderRecurrence.valueOf(recurrenceType),
        weeklyDaysMask = weeklyDaysMask,
        isCompleted = isCompleted,
        endDateTimeMillis = endDateTime
    )
}

private fun Reminder.toEntity(): ReminderEntity {
    return ReminderEntity(
        id = id,
        title = title,
        description = description.takeIf { it.isNotBlank() },
        anchorDateTime = anchorDateTimeMillis,
        recurrenceType = recurrence.name,
        weeklyDaysMask = weeklyDaysMask,
        isCompleted = isCompleted,
        endDateTime = endDateTimeMillis
    )
}

private fun Reminder.sortKey(): Long {
    return nextOccurrenceMillis() ?: anchorDateTimeMillis ?: Long.MAX_VALUE
}
