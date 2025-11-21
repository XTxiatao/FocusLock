package com.focuslock.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LockScheduleEntity::class], version = 1, exportSchema = false)
abstract class LockScheduleDatabase : RoomDatabase() {
    abstract fun lockScheduleDao(): LockScheduleDao
}
