package com.focuslock.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        LockScheduleEntity::class,
        WhitelistedAppEntity::class,
        AppRestrictionPlanEntity::class,
        AppRestrictionPlanAppCrossRef::class
    ],
    version = 3,
    exportSchema = false
)
abstract class LockScheduleDatabase : RoomDatabase() {
    abstract fun lockScheduleDao(): LockScheduleDao
    abstract fun whitelistedAppDao(): WhitelistedAppDao
    abstract fun appRestrictionPlanDao(): AppRestrictionPlanDao
}
