package com.focuslock.accessibility

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class FocusLockAccessibilityService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // Placeholder so the service stays active while the overlay service runs.
    }

    override fun onInterrupt() {
    }
}
