package com.focuslock.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u000fJ\u0012\u0010\u001f\u001a\u0004\u0018\u00010 2\b\b\u0002\u0010!\u001a\u00020\"J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0005H\u00c6\u0003J\t\u0010%\u001a\u00020\u0005H\u00c6\u0003J\u0010\u0010&\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0011J\t\u0010\'\u001a\u00020\tH\u00c6\u0003J\t\u0010(\u001a\u00020\u000bH\u00c6\u0003J\t\u0010)\u001a\u00020\rH\u00c6\u0003J\u0010\u0010*\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0011Jb\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010,J\u0013\u0010-\u001a\u00020\r2\b\u0010.\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010/\u001a\u00020\u000bH\u00d6\u0001J\r\u00100\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0011J\u0017\u00101\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u00102\u001a\u00020\u0003\u00a2\u0006\u0002\u00103J\f\u00104\u001a\b\u0012\u0004\u0012\u00020605J\t\u00107\u001a\u00020\u0005H\u00d6\u0001R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\r8F\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e\u00a8\u00068"}, d2 = {"Lcom/focuslock/model/Reminder;", "", "id", "", "title", "", "description", "anchorDateTimeMillis", "recurrence", "Lcom/focuslock/model/ReminderRecurrence;", "weeklyDaysMask", "", "isCompleted", "", "endDateTimeMillis", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/Long;Lcom/focuslock/model/ReminderRecurrence;IZLjava/lang/Long;)V", "getAnchorDateTimeMillis", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getDescription", "()Ljava/lang/String;", "getEndDateTimeMillis", "getId", "()J", "()Z", "isRepeating", "getRecurrence", "()Lcom/focuslock/model/ReminderRecurrence;", "getTitle", "getWeeklyDaysMask", "()I", "anchorDateTime", "Ljava/time/ZonedDateTime;", "zoneId", "Ljava/time/ZoneId;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/Long;Lcom/focuslock/model/ReminderRecurrence;IZLjava/lang/Long;)Lcom/focuslock/model/Reminder;", "equals", "other", "hashCode", "nextCycleAnchorMillis", "nextOccurrenceMillis", "nowMillis", "(J)Ljava/lang/Long;", "selectedDays", "", "Ljava/time/DayOfWeek;", "toString", "app_debug"})
public final class Reminder {
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String title = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String description = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long anchorDateTimeMillis = null;
    @org.jetbrains.annotations.NotNull()
    private final com.focuslock.model.ReminderRecurrence recurrence = null;
    private final int weeklyDaysMask = 0;
    private final boolean isCompleted = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long endDateTimeMillis = null;
    
    public Reminder(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.Nullable()
    java.lang.Long anchorDateTimeMillis, @org.jetbrains.annotations.NotNull()
    com.focuslock.model.ReminderRecurrence recurrence, int weeklyDaysMask, boolean isCompleted, @org.jetbrains.annotations.Nullable()
    java.lang.Long endDateTimeMillis) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getAnchorDateTimeMillis() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.focuslock.model.ReminderRecurrence getRecurrence() {
        return null;
    }
    
    public final int getWeeklyDaysMask() {
        return 0;
    }
    
    public final boolean isCompleted() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getEndDateTimeMillis() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.time.ZonedDateTime anchorDateTime(@org.jetbrains.annotations.NotNull()
    java.time.ZoneId zoneId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.time.DayOfWeek> selectedDays() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long nextOccurrenceMillis(long nowMillis) {
        return null;
    }
    
    public final boolean isRepeating() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long nextCycleAnchorMillis() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.focuslock.model.ReminderRecurrence component5() {
        return null;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final boolean component7() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.focuslock.model.Reminder copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.Nullable()
    java.lang.Long anchorDateTimeMillis, @org.jetbrains.annotations.NotNull()
    com.focuslock.model.ReminderRecurrence recurrence, int weeklyDaysMask, boolean isCompleted, @org.jetbrains.annotations.Nullable()
    java.lang.Long endDateTimeMillis) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}