package com.focuslock

import android.app.Application
import androidx.room.Room
import com.focuslock.data.LockScheduleDatabase
import com.focuslock.data.LockScheduleRepository

class FocusLockApplication : Application() {

    val lockScheduleRepository by lazy {
        LockScheduleRepository(lockScheduleDatabase.lockScheduleDao())
    }

    private val lockScheduleDatabase by lazy {
        Room.databaseBuilder(this, LockScheduleDatabase::class.java, "focus_lock.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}
