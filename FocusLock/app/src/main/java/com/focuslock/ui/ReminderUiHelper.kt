package com.focuslock.ui

import com.focuslock.data.ReminderRepository
import com.focuslock.model.Reminder
import java.time.ZoneId

object ReminderGrouping {

    fun buildDayGroups(reminders: List<Reminder>): List<ReminderDayGroup> {
        val zone = ZoneId.systemDefault()
        return reminders
            .filter { it.anchorDateTimeMillis != null }
            .groupBy { java.time.Instant.ofEpochMilli(it.anchorDateTimeMillis!!).atZone(zone).toLocalDate() }
            .toSortedMap()
            .map { (date, list) ->
                val startOfDay = date.atStartOfDay(zone).toInstant().toEpochMilli()
                ReminderDayGroup(
                    dateMillis = startOfDay,
                    reminders = list.sortedBy { it.anchorDateTimeMillis }
                )
            }
    }

    fun buildFloatingGroup(reminders: List<Reminder>): List<ReminderDayGroup> =
        reminders.takeIf { it.isNotEmpty() }?.let {
            listOf(
                ReminderDayGroup(
                    dateMillis = Long.MAX_VALUE,
                    reminders = it.sortedBy { reminder -> reminder.title }
                )
            )
        } ?: emptyList()

    fun buildCompletedGroups(reminders: List<Reminder>): List<CompletedDayGroup> {
        val zone = ZoneId.systemDefault()
        return reminders
            .filter { it.anchorDateTimeMillis != null }
            .groupBy { java.time.Instant.ofEpochMilli(it.anchorDateTimeMillis!!).atZone(zone).toLocalDate() }
            .toSortedMap()
            .map { (date, list) ->
                val startOfDay = date.atStartOfDay(zone).toInstant().toEpochMilli()
                CompletedDayGroup(
                    dateMillis = startOfDay,
                    reminders = list.sortedBy { it.anchorDateTimeMillis }
                )
            }
    }

    fun buildCompletedFloatingGroup(reminders: List<Reminder>): List<CompletedDayGroup> {
        return reminders.takeIf { it.isNotEmpty() }?.let {
            listOf(
                CompletedDayGroup(
                    dateMillis = Long.MAX_VALUE,
                    reminders = it.sortedBy { reminder -> reminder.title }
                )
            )
        } ?: emptyList()
    }
}

suspend fun ReminderRepository.completeReminder(reminder: Reminder) {
    if (!reminder.isRepeating) {
        updateReminder(reminder.copy(isCompleted = true))
    } else {
        val nextAnchor = reminder.nextCycleAnchorMillis()
        if (nextAnchor == null) {
            updateReminder(reminder.copy(isCompleted = true))
        } else {
            val endReached = reminder.endDateTimeMillis?.let { nextAnchor > it } ?: false
            val updated = if (endReached) {
                reminder.copy(
                    anchorDateTimeMillis = nextAnchor,
                    isCompleted = true
                )
            } else {
                reminder.copy(
                    anchorDateTimeMillis = nextAnchor
                )
            }
            updateReminder(updated)
        }
    }
}
