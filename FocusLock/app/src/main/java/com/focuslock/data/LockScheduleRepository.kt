package com.focuslock.data

import com.focuslock.model.LockSchedule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LockScheduleRepository(
    private val dao: LockScheduleDao
) {

    val schedulesFlow: Flow<List<LockSchedule>> = dao.getAll().map { list ->
        list.map { it.toModel() }
    }

    val enabledSchedulesFlow: Flow<List<LockSchedule>> = dao.getEnabled().map { list ->
        list.map { it.toModel() }
    }

    suspend fun insert(schedule: LockSchedule) {
        withContext(Dispatchers.IO) {
            dao.insert(schedule.toEntity())
        }
    }

    suspend fun update(schedule: LockSchedule) {
        withContext(Dispatchers.IO) {
            dao.update(schedule.toEntity())
        }
    }

    suspend fun delete(schedule: LockSchedule) {
        withContext(Dispatchers.IO) {
            dao.delete(schedule.toEntity())
        }
    }
}

private fun LockScheduleEntity.toModel(): LockSchedule {
    return LockSchedule(
        id = id,
        startMinutes = startMinutes,
        endMinutes = endMinutes,
        daysBitmask = daysBitmask,
        isEnabled = isEnabled
    )
}

private fun LockSchedule.toEntity(): LockScheduleEntity {
    return LockScheduleEntity(
        id = id,
        startMinutes = startMinutes,
        endMinutes = endMinutes,
        daysBitmask = daysBitmask,
        isEnabled = isEnabled
    )
}
