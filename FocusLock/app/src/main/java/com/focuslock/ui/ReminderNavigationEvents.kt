package com.focuslock.ui

object ReminderNavigationEvents {

    @Volatile
    private var pendingReminderId: Long? = null

    fun requestReminderEdit(reminderId: Long) {
        pendingReminderId = reminderId
    }

    fun consumeReminderEditRequest(): Long? {
        val id = pendingReminderId
        pendingReminderId = null
        return id
    }
}
