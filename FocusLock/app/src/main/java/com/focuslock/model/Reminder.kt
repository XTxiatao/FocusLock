package com.focuslock.model

import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.TemporalAdjusters
import kotlin.math.min

data class Reminder(
    val id: Long = 0,
    val title: String,
    val description: String,
    val anchorDateTimeMillis: Long,
    val recurrence: ReminderRecurrence,
    val weeklyDaysMask: Int,
    val isActive: Boolean,
    val isCompleted: Boolean,
    val isArchived: Boolean,
    val createdAtMillis: Long,
    val updatedAtMillis: Long
) {

    fun anchorDateTime(zoneId: ZoneId = ZoneId.systemDefault()): ZonedDateTime {
        return Instant.ofEpochMilli(anchorDateTimeMillis).atZone(zoneId)
    }

    fun selectedDays(): List<DayOfWeek> {
        if (recurrence != ReminderRecurrence.WEEKLY) return emptyList()
        return DayOfWeek.values().filter { weeklyDaysMask.hasDay(it) }
    }

    fun nextOccurrenceMillis(nowMillis: Long = System.currentTimeMillis()): Long? {
        val zone = ZoneId.systemDefault()
        val anchor = anchorDateTime(zone)
        val reference = Instant.ofEpochMilli(nowMillis).atZone(zone)
        val effectiveReference = if (reference.isBefore(anchor)) anchor else reference
        val time = anchor.toLocalTime()
        return when (recurrence) {
            ReminderRecurrence.NONE -> anchorDateTimeMillis
            ReminderRecurrence.DAILY -> computeDailyOccurrence(effectiveReference, time)
            ReminderRecurrence.WEEKLY -> {
                val targetDays = selectedDays().ifEmpty { listOf(anchor.dayOfWeek) }
                computeWeeklyOccurrence(effectiveReference, time, targetDays)
            }
            ReminderRecurrence.MONTHLY -> computeMonthlyOccurrence(effectiveReference, time, anchor.dayOfMonth)
            ReminderRecurrence.YEARLY -> computeYearlyOccurrence(effectiveReference, time, anchor.monthValue, anchor.dayOfMonth)
        }
    }

    val isRepeating: Boolean
        get() = recurrence.isRepeating
}

private fun computeDailyOccurrence(reference: ZonedDateTime, time: LocalTime): Long {
    var candidate = reference.withHour(time.hour).withMinute(time.minute).withSecond(0).withNano(0)
    if (candidate.isBefore(reference)) {
        candidate = candidate.plusDays(1)
    }
    return candidate.toInstant().toEpochMilli()
}

private fun computeWeeklyOccurrence(reference: ZonedDateTime, time: LocalTime, days: List<DayOfWeek>): Long {
    var best: ZonedDateTime? = null
    for (day in days) {
        var candidate = reference.with(TemporalAdjusters.nextOrSame(day))
        candidate = candidate.withHour(time.hour).withMinute(time.minute).withSecond(0).withNano(0)
        if (candidate.isBefore(reference)) {
            candidate = candidate.plusWeeks(1)
        }
        if (best == null || candidate.isBefore(best)) {
            best = candidate
        }
    }
    return (best ?: reference).toInstant().toEpochMilli()
}

private fun computeMonthlyOccurrence(reference: ZonedDateTime, time: LocalTime, targetDay: Int): Long {
    var yearMonth = YearMonth.of(reference.year, reference.monthValue)
    var day = min(targetDay, yearMonth.lengthOfMonth())
    var candidateDate = yearMonth.atDay(day)
    var candidate = candidateDate.atTime(time).atZone(reference.zone)
    if (candidate.isBefore(reference)) {
        yearMonth = yearMonth.plusMonths(1)
        day = min(targetDay, yearMonth.lengthOfMonth())
        candidateDate = yearMonth.atDay(day)
        candidate = candidateDate.atTime(time).atZone(reference.zone)
    }
    return candidate.toInstant().toEpochMilli()
}

private fun computeYearlyOccurrence(reference: ZonedDateTime, time: LocalTime, month: Int, day: Int): Long {
    var candidateYear = reference.year
    var candidate = buildYearlyCandidate(candidateYear, month, day, time, reference.zone)
    if (candidate.isBefore(reference)) {
        candidateYear += 1
        candidate = buildYearlyCandidate(candidateYear, month, day, time, reference.zone)
    }
    return candidate.toInstant().toEpochMilli()
}

private fun buildYearlyCandidate(
    year: Int,
    month: Int,
    day: Int,
    time: LocalTime,
    zone: ZoneId
): ZonedDateTime {
    val yearMonth = YearMonth.of(year, month)
    val safeDay = min(day, yearMonth.lengthOfMonth())
    val date = LocalDate.of(year, month, safeDay)
    return date.atTime(time).atZone(zone)
}

private fun Int.hasDay(day: DayOfWeek): Boolean {
    val bit = 1 shl day.ordinal
    return this and bit != 0
}
