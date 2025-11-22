package com.focuslock.model

import java.time.LocalDateTime

data class AppRestrictionPlan(
    val id: Long = 0,
    val isEnabled: Boolean,
    val slots: List<AppRestrictionPlanSlot>,
    val apps: List<WhitelistedApp>
) {

    fun matches(now: LocalDateTime): Boolean {
        if (!isEnabled || slots.isEmpty()) {
            return false
        }
        return slots.any { it.matches(now) }
    }

    fun hasSlots(): Boolean = slots.isNotEmpty()
}
