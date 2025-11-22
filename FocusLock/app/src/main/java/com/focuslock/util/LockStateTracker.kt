package com.focuslock.util

object LockStateTracker {
    @Volatile
    var enforceHome: Boolean = false

    @Volatile
    var currentForegroundPackage: String? = null
}
