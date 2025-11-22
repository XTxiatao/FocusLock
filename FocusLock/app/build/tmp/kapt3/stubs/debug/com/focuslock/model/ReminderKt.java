package com.focuslock.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001a0\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002\u001a\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a \u0010\r\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0003H\u0002\u001a&\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002\u001a(\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0002\u001a\u0014\u0010\u0014\u001a\u00020\u0015*\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0012H\u0002\u00a8\u0006\u0016"}, d2 = {"buildYearlyCandidate", "Ljava/time/ZonedDateTime;", "year", "", "month", "day", "time", "Ljava/time/LocalTime;", "zone", "Ljava/time/ZoneId;", "computeDailyOccurrence", "", "reference", "computeMonthlyOccurrence", "targetDay", "computeWeeklyOccurrence", "days", "", "Ljava/time/DayOfWeek;", "computeYearlyOccurrence", "hasDay", "", "app_debug"})
public final class ReminderKt {
    
    private static final long computeDailyOccurrence(java.time.ZonedDateTime reference, java.time.LocalTime time) {
        return 0L;
    }
    
    private static final long computeWeeklyOccurrence(java.time.ZonedDateTime reference, java.time.LocalTime time, java.util.List<? extends java.time.DayOfWeek> days) {
        return 0L;
    }
    
    private static final long computeMonthlyOccurrence(java.time.ZonedDateTime reference, java.time.LocalTime time, int targetDay) {
        return 0L;
    }
    
    private static final long computeYearlyOccurrence(java.time.ZonedDateTime reference, java.time.LocalTime time, int month, int day) {
        return 0L;
    }
    
    private static final java.time.ZonedDateTime buildYearlyCandidate(int year, int month, int day, java.time.LocalTime time, java.time.ZoneId zone) {
        return null;
    }
    
    private static final boolean hasDay(int $this$hasDay, java.time.DayOfWeek day) {
        return false;
    }
}