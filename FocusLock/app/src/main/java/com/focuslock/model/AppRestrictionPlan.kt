package com.focuslock.model

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.util.Locale

data class AppRestrictionPlan(
    val id: Long = 0,
    val startMinutes: Int,
    val endMinutes: Int,
    val daysBitmask: Int,
    val isEnabled: Boolean,
    val apps: List<WhitelistedApp>
) {

    fun matches(now: LocalDateTime): Boolean {
        if (!isEnabled || !isDaySelected(now.dayOfWeek)) {
            return false
        }
        val currentMinute = now.hour * 60 + now.minute
        return if (startMinutes <= endMinutes) {
            currentMinute in startMinutes..endMinutes
        } else {
            currentMinute >= startMinutes || currentMinute <= endMinutes
        }
    }

    fun isDaySelected(day: DayOfWeek): Boolean {
        val bit = 1 shl day.ordinal
        return daysBitmask and bit != 0
    }

    fun dayLabels(): String {
        val names = DayOfWeek.values()
            .filter { isDaySelected(it) }
            .map { it.getDisplayName(java.time.format.TextStyle.SHORT, Locale.ENGLISH) }
        return if (names.isEmpty()) "No days" else names.joinToString(" ")
    }

    fun rangeLabel(): String {
        val startHour = startMinutes / 60
        val startMinute = startMinutes % 60
        val endHour = endMinutes / 60
        val endMinute = endMinutes % 60
        return String.format(Locale.ENGLISH, "%02d:%02d - %02d:%02d", startHour, startMinute, endHour, endMinute)
    }
}
