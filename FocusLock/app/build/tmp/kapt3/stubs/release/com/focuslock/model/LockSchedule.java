package com.focuslock.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\tH\u00c6\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\tH\u00c6\u0001J\u0006\u0010\u0018\u001a\u00020\u0019J\u0013\u0010\u001a\u001a\u00020\t2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u0005H\u00d6\u0001J\u000e\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\t2\u0006\u0010!\u001a\u00020\"J\u0006\u0010#\u001a\u00020\u0019J\t\u0010$\u001a\u00020\u0019H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f\u00a8\u0006%"}, d2 = {"Lcom/focuslock/model/LockSchedule;", "", "id", "", "startMinutes", "", "endMinutes", "daysBitmask", "isEnabled", "", "(JIIIZ)V", "getDaysBitmask", "()I", "getEndMinutes", "getId", "()J", "()Z", "getStartMinutes", "component1", "component2", "component3", "component4", "component5", "copy", "dayLabels", "", "equals", "other", "hashCode", "isDaySelected", "day", "Ljava/time/DayOfWeek;", "matches", "now", "Ljava/time/LocalDateTime;", "rangeLabel", "toString", "app_release"})
public final class LockSchedule {
    private final long id = 0L;
    private final int startMinutes = 0;
    private final int endMinutes = 0;
    private final int daysBitmask = 0;
    private final boolean isEnabled = false;
    
    public LockSchedule(long id, int startMinutes, int endMinutes, int daysBitmask, boolean isEnabled) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    public final int getStartMinutes() {
        return 0;
    }
    
    public final int getEndMinutes() {
        return 0;
    }
    
    public final int getDaysBitmask() {
        return 0;
    }
    
    public final boolean isEnabled() {
        return false;
    }
    
    public final boolean isDaySelected(@org.jetbrains.annotations.NotNull()
    java.time.DayOfWeek day) {
        return false;
    }
    
    public final boolean matches(@org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime now) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String dayLabels() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String rangeLabel() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.focuslock.model.LockSchedule copy(long id, int startMinutes, int endMinutes, int daysBitmask, boolean isEnabled) {
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