package com.focuslock.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\tH\u00c6\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00c6\u0003JK\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00c6\u0001J\u0006\u0010\u001e\u001a\u00020\u001fJ\u0013\u0010 \u001a\u00020\t2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020\u0005H\u00d6\u0001J\u000e\u0010#\u001a\u00020\t2\u0006\u0010$\u001a\u00020%J\u000e\u0010&\u001a\u00020\t2\u0006\u0010\'\u001a\u00020(J\u0006\u0010)\u001a\u00020\u001fJ\t\u0010*\u001a\u00020\u001fH\u00d6\u0001R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011\u00a8\u0006+"}, d2 = {"Lcom/focuslock/model/AppRestrictionPlan;", "", "id", "", "startMinutes", "", "endMinutes", "daysBitmask", "isEnabled", "", "apps", "", "Lcom/focuslock/model/WhitelistedApp;", "(JIIIZLjava/util/List;)V", "getApps", "()Ljava/util/List;", "getDaysBitmask", "()I", "getEndMinutes", "getId", "()J", "()Z", "getStartMinutes", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "dayLabels", "", "equals", "other", "hashCode", "isDaySelected", "day", "Ljava/time/DayOfWeek;", "matches", "now", "Ljava/time/LocalDateTime;", "rangeLabel", "toString", "app_release"})
public final class AppRestrictionPlan {
    private final long id = 0L;
    private final int startMinutes = 0;
    private final int endMinutes = 0;
    private final int daysBitmask = 0;
    private final boolean isEnabled = false;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.focuslock.model.WhitelistedApp> apps = null;
    
    public AppRestrictionPlan(long id, int startMinutes, int endMinutes, int daysBitmask, boolean isEnabled, @org.jetbrains.annotations.NotNull()
    java.util.List<com.focuslock.model.WhitelistedApp> apps) {
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.focuslock.model.WhitelistedApp> getApps() {
        return null;
    }
    
    public final boolean matches(@org.jetbrains.annotations.NotNull()
    java.time.LocalDateTime now) {
        return false;
    }
    
    public final boolean isDaySelected(@org.jetbrains.annotations.NotNull()
    java.time.DayOfWeek day) {
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
    public final java.util.List<com.focuslock.model.WhitelistedApp> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.focuslock.model.AppRestrictionPlan copy(long id, int startMinutes, int endMinutes, int daysBitmask, boolean isEnabled, @org.jetbrains.annotations.NotNull()
    java.util.List<com.focuslock.model.WhitelistedApp> apps) {
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