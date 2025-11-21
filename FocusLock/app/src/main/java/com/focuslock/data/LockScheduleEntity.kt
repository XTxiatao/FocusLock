package com.focuslock.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lock_schedule")
data class LockScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "start_minutes")
    val startMinutes: Int,
    @ColumnInfo(name = "end_minutes")
    val endMinutes: Int,
    @ColumnInfo(name = "days_bitmask")
    val daysBitmask: Int,
    @ColumnInfo(name = "is_enabled")
    val isEnabled: Boolean
)
