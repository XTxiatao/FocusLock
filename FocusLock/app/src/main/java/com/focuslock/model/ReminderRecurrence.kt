package com.focuslock.model

enum class ReminderRecurrence {
    NONE,
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY;

    val isRepeating: Boolean
        get() = this != NONE
}
