package com.focuslock.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B_\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0012J\u0010\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0005H\u00c6\u0003J\t\u0010)\u001a\u00020\u0005H\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\tH\u00c6\u0003J\t\u0010,\u001a\u00020\u000bH\u00c6\u0003J\t\u0010-\u001a\u00020\rH\u00c6\u0003J\t\u0010.\u001a\u00020\rH\u00c6\u0003J\t\u0010/\u001a\u00020\rH\u00c6\u0003Jw\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u0003H\u00c6\u0001J\u0013\u00101\u001a\u00020\r2\b\u00102\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00103\u001a\u00020\u000bH\u00d6\u0001J\u0017\u00104\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u00105\u001a\u00020\u0003\u00a2\u0006\u0002\u00106J\f\u00107\u001a\b\u0012\u0004\u0012\u00020908J\t\u0010:\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0010\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0019R\u0011\u0010\u000f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0019R\u0011\u0010\u000e\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\r8F\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 \u00a8\u0006;"}, d2 = {"Lcom/focuslock/model/Reminder;", "", "id", "", "title", "", "description", "anchorDateTimeMillis", "recurrence", "Lcom/focuslock/model/ReminderRecurrence;", "weeklyDaysMask", "", "isActive", "", "isCompleted", "isArchived", "createdAtMillis", "updatedAtMillis", "(JLjava/lang/String;Ljava/lang/String;JLcom/focuslock/model/ReminderRecurrence;IZZZJJ)V", "getAnchorDateTimeMillis", "()J", "getCreatedAtMillis", "getDescription", "()Ljava/lang/String;", "getId", "()Z", "isRepeating", "getRecurrence", "()Lcom/focuslock/model/ReminderRecurrence;", "getTitle", "getUpdatedAtMillis", "getWeeklyDaysMask", "()I", "anchorDateTime", "Ljava/time/ZonedDateTime;", "zoneId", "Ljava/time/ZoneId;", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "nextOccurrenceMillis", "nowMillis", "(J)Ljava/lang/Long;", "selectedDays", "", "Ljava/time/DayOfWeek;", "toString", "app_debug"})
public final class Reminder {
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String title = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String description = null;
    private final long anchorDateTimeMillis = 0L;
    @org.jetbrains.annotations.NotNull()
    private final com.focuslock.model.ReminderRecurrence recurrence = null;
    private final int weeklyDaysMask = 0;
    private final boolean isActive = false;
    private final boolean isCompleted = false;
    private final boolean isArchived = false;
    private final long createdAtMillis = 0L;
    private final long updatedAtMillis = 0L;
    
    public Reminder(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, long anchorDateTimeMillis, @org.jetbrains.annotations.NotNull()
    com.focuslock.model.ReminderRecurrence recurrence, int weeklyDaysMask, boolean isActive, boolean isCompleted, boolean isArchived, long createdAtMillis, long updatedAtMillis) {
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
    
    public final long getAnchorDateTimeMillis() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.focuslock.model.ReminderRecurrence getRecurrence() {
        return null;
    }
    
    public final int getWeeklyDaysMask() {
        return 0;
    }
    
    public final boolean isActive() {
        return false;
    }
    
    public final boolean isCompleted() {
        return false;
    }
    
    public final boolean isArchived() {
        return false;
    }
    
    public final long getCreatedAtMillis() {
        return 0L;
    }
    
    public final long getUpdatedAtMillis() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
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
    
    public final long component1() {
        return 0L;
    }
    
    public final long component10() {
        return 0L;
    }
    
    public final long component11() {
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
    
    public final long component4() {
        return 0L;
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
    
    public final boolean component8() {
        return false;
    }
    
    public final boolean component9() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.focuslock.model.Reminder copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, long anchorDateTimeMillis, @org.jetbrains.annotations.NotNull()
    com.focuslock.model.ReminderRecurrence recurrence, int weeklyDaysMask, boolean isActive, boolean isCompleted, boolean isArchived, long createdAtMillis, long updatedAtMillis) {
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