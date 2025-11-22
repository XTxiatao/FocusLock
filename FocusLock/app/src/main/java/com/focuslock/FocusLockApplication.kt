package com.focuslock

import android.app.Application
import androidx.room.Room
import com.focuslock.data.AppRestrictionPlanRepository
import com.focuslock.data.LockScheduleDatabase
import com.focuslock.data.LockScheduleRepository
import com.focuslock.data.ReminderRepository
import com.focuslock.data.WhitelistedAppRepository

class FocusLockApplication : Application() {

    val lockScheduleRepository by lazy {
        LockScheduleRepository(lockScheduleDatabase.lockScheduleDao())
    }

    val whitelistedAppRepository by lazy {
        WhitelistedAppRepository(lockScheduleDatabase.whitelistedAppDao())
    }

    val appRestrictionPlanRepository by lazy {
        AppRestrictionPlanRepository(lockScheduleDatabase.appRestrictionPlanDao())
    }

    val reminderRepository by lazy {
        ReminderRepository(lockScheduleDatabase.reminderDao())
    }

    private val lockScheduleDatabase by lazy {
        Room.databaseBuilder(this, LockScheduleDatabase::class.java, "focus_lock.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}
