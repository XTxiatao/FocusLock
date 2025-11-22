package com.focuslock.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LockScheduleEntity::class, WhitelistedAppEntity::class], version = 2, exportSchema = false)
abstract class LockScheduleDatabase : RoomDatabase() {
    abstract fun lockScheduleDao(): LockScheduleDao
    abstract fun whitelistedAppDao(): WhitelistedAppDao
}
