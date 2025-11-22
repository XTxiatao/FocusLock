package com.focuslock.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000>\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u001a0\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a\u0018\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0002\u001a \u0010\u000e\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0005H\u0002\u001a&\u0010\u0010\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002\u001a(\u0010\u0014\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0002\u001a\u0014\u0010\u0015\u001a\u00020\u0001*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0003H\u0002\u001a\u0014\u0010\u0018\u001a\u00020\u0001*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0003H\u0002\u001a\u0014\u0010\u0019\u001a\u00020\u0001*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0003H\u0002\u001a\u0014\u0010\u001a\u001a\u00020\u001b*\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0013H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"ONE_DAY_MILLIS", "", "buildYearlyCandidate", "Ljava/time/ZonedDateTime;", "year", "", "month", "day", "time", "Ljava/time/LocalTime;", "zone", "Ljava/time/ZoneId;", "computeDailyOccurrence", "reference", "computeMonthlyOccurrence", "targetDay", "computeWeeklyOccurrence", "days", "", "Ljava/time/DayOfWeek;", "computeYearlyOccurrence", "computeNextMonthlyAnchor", "Lcom/focuslock/model/Reminder;", "anchor", "computeNextWeeklyAnchor", "computeNextYearlyAnchor", "hasDay", "", "app_release"})
public final class ReminderKt {
    private static final long ONE_DAY_MILLIS = 86400000L;
    
    private static final long computeNextWeeklyAnchor(com.focuslock.model.Reminder $this$computeNextWeeklyAnchor, java.time.ZonedDateTime anchor) {
        return 0L;
    }
    
    private static final long computeNextMonthlyAnchor(com.focuslock.model.Reminder $this$computeNextMonthlyAnchor, java.time.ZonedDateTime anchor) {
        return 0L;
    }
    
    private static final long computeNextYearlyAnchor(com.focuslock.model.Reminder $this$computeNextYearlyAnchor, java.time.ZonedDateTime anchor) {
        return 0L;
    }
    
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