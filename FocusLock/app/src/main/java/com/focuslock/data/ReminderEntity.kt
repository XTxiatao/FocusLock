package com.focuslock.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "reminders",
    indices = [
        Index(value = ["title"], unique = true)
    ]
)
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String?,
    @ColumnInfo(name = "anchor_date_time")
    val anchorDateTime: Long,
    @ColumnInfo(name = "recurrence_type")
    val recurrenceType: String,
    @ColumnInfo(name = "weekly_days_mask")
    val weeklyDaysMask: Int,
    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean,
    @ColumnInfo(name = "end_date_time")
    val endDateTime: Long?
)
