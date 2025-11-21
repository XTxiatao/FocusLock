package com.focuslock.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LockScheduleDao {
    @Insert
    fun insert(schedule: LockScheduleEntity): Long

    @Update
    fun update(schedule: LockScheduleEntity)

    @Query("DELETE FROM lock_schedule WHERE id = :scheduleId")
    fun delete(scheduleId: Long)

    @Query("SELECT * FROM lock_schedule ORDER BY id DESC")
    fun getAll(): Flow<List<LockScheduleEntity>>

    @Query("SELECT * FROM lock_schedule WHERE is_enabled = 1 ORDER BY id DESC")
    fun getEnabled(): Flow<List<LockScheduleEntity>>
}
