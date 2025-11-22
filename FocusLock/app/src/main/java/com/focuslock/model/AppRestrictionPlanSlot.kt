package com.focuslock.model

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.util.Locale

data class AppRestrictionPlanSlot(
    val startMinutes: Int,
    val endMinutes: Int,
    val daysBitmask: Int
) {

    fun matches(now: LocalDateTime): Boolean {
        val currentMinute = now.hour * 60 + now.minute
        if (startMinutes <= endMinutes) {
            return isDaySelected(now.dayOfWeek) && currentMinute in startMinutes..endMinutes
        }
        val todaySelected = isDaySelected(now.dayOfWeek)
        val previousSelected = isDaySelected(now.dayOfWeek.minus(1))
        if (!todaySelected && !previousSelected) {
            return false
        }
        val inTodaySegment = todaySelected && currentMinute >= startMinutes
        val inPreviousSegment = previousSelected && currentMinute <= endMinutes
        return inTodaySegment || inPreviousSegment
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

    fun toDaySet(): Set<DayOfWeek> {
        return DayOfWeek.values().filter { isDaySelected(it) }.toSet()
    }
}
