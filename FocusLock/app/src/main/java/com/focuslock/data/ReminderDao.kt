package com.focuslock.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {

    @Query("SELECT * FROM reminders")
    fun observeAll(): Flow<List<ReminderEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(reminder: ReminderEntity): Long

    @Update
    fun update(reminder: ReminderEntity)

    @Delete
    fun delete(reminder: ReminderEntity)

    @Query("SELECT COUNT(*) FROM reminders WHERE LOWER(title) = LOWER(:title)")
    fun countByTitle(title: String): Int

    @Query("SELECT COUNT(*) FROM reminders WHERE LOWER(title) = LOWER(:title) AND id != :excludeId")
    fun countByTitleExcluding(title: String, excludeId: Long): Int
}
