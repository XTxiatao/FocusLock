package com.focuslock.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        LockScheduleEntity::class,
        WhitelistedAppEntity::class,
        AppRestrictionPlanEntity::class,
        AppRestrictionPlanSlotEntity::class,
        AppRestrictionPlanAppCrossRef::class,
        ReminderEntity::class
    ],
    version = 7,
    exportSchema = false
)
abstract class LockScheduleDatabase : RoomDatabase() {
    abstract fun lockScheduleDao(): LockScheduleDao
    abstract fun whitelistedAppDao(): WhitelistedAppDao
    abstract fun appRestrictionPlanDao(): AppRestrictionPlanDao
    abstract fun reminderDao(): ReminderDao
}
