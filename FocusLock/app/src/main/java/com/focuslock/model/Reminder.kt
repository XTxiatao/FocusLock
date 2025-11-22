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
    val isCompleted: Boolean,
    val endDateTimeMillis: Long?
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

    fun nextCycleAnchorMillis(): Long {
        if (!isRepeating) {
            return anchorDateTimeMillis
        }
        val anchor = anchorDateTime()
        return when (recurrence) {
            ReminderRecurrence.DAILY -> anchor.plusDays(1).toInstant().toEpochMilli()
            ReminderRecurrence.WEEKLY -> computeNextWeeklyAnchor(anchor)
            ReminderRecurrence.MONTHLY -> computeNextMonthlyAnchor(anchor)
            ReminderRecurrence.YEARLY -> computeNextYearlyAnchor(anchor)
            ReminderRecurrence.NONE -> anchorDateTimeMillis
        }
    }
}

private fun Reminder.computeNextWeeklyAnchor(anchor: ZonedDateTime): Long {
    val days = selectedDays().ifEmpty { listOf(anchor.dayOfWeek) }.sortedBy { it.ordinal }
    val current = anchor.dayOfWeek
    val nextDay = days.firstOrNull { it.ordinal > current.ordinal } ?: days.first()
    val delta = ((nextDay.ordinal - current.ordinal + 7) % 7).let { if (it == 0) 7 else it }
    return anchor.plusDays(delta.toLong()).toInstant().toEpochMilli()
}

private fun Reminder.computeNextMonthlyAnchor(anchor: ZonedDateTime): Long {
    val currentDay = anchor.dayOfMonth
    val nextMonth = anchor.plusMonths(1)
    val ym = YearMonth.of(nextMonth.year, nextMonth.month)
    val safeDay = min(currentDay, ym.lengthOfMonth())
    val adjusted = nextMonth.withDayOfMonth(safeDay)
    return adjusted.toInstant().toEpochMilli()
}

private fun Reminder.computeNextYearlyAnchor(anchor: ZonedDateTime): Long {
    val currentDay = anchor.dayOfMonth
    val currentMonth = anchor.monthValue
    val nextYear = anchor.plusYears(1)
    val ym = YearMonth.of(nextYear.year, currentMonth)
    val safeDay = min(currentDay, ym.lengthOfMonth())
    val date = LocalDate.of(nextYear.year, currentMonth, safeDay)
    return date.atTime(anchor.toLocalTime()).atZone(anchor.zone).toInstant().toEpochMilli()
}

private const val ONE_DAY_MILLIS = 24 * 60 * 60 * 1000L

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
