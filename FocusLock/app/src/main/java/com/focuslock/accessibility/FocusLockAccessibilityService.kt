package com.focuslock.accessibility

import android.accessibilityservice.AccessibilityService
import android.os.SystemClock
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import com.focuslock.util.LockStateTracker

class FocusLockAccessibilityService : AccessibilityService() {

    private val targetPackages = setOf(
        "com.android.systemui", // recents, notifications, etc.
        "com.android.settings"
    )
    private var lastHomeTriggerTime = 0L

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return
        if (event.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            return
        }

        val packageName = event.packageName?.toString() ?: return
        LockStateTracker.currentForegroundPackage = packageName
        if (!targetPackages.contains(packageName)) {
            return
        }

        if (!LockStateTracker.enforceHome) {
            return
        }

        val now = SystemClock.uptimeMillis()
        if (now - lastHomeTriggerTime < 2000) {
            return
        }
        lastHomeTriggerTime = now
        Log.d("FocusLockAccessibility", "Detected $packageName. Triggering HOME action.")
        performGlobalAction(GLOBAL_ACTION_HOME)
    }

    override fun onInterrupt() {
    }
}
