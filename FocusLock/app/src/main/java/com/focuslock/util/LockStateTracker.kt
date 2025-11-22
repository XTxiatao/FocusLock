package com.focuslock.util

object LockStateTracker {
    @Volatile
    var enforceHome: Boolean = false
}
