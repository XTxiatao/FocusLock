package com.focuslock.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0014"}, d2 = {"Lcom/focuslock/data/AppRestrictionPlanAppCrossRef;", "", "planId", "", "packageName", "", "(JLjava/lang/String;)V", "getPackageName", "()Ljava/lang/String;", "getPlanId", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"})
@androidx.room.Entity(tableName = "app_restriction_plan_apps", primaryKeys = {"planId", "packageName"}, indices = {@androidx.room.Index(value = {"planId"}), @androidx.room.Index(value = {"packageName"})}, foreignKeys = {@androidx.room.ForeignKey(entity = com.focuslock.data.AppRestrictionPlanEntity.class, parentColumns = {"id"}, childColumns = {"planId"}, onDelete = 5), @androidx.room.ForeignKey(entity = com.focuslock.data.WhitelistedAppEntity.class, parentColumns = {"packageName"}, childColumns = {"packageName"}, onDelete = 5)})
public final class AppRestrictionPlanAppCrossRef {
    private final long planId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String packageName = null;
    
    public AppRestrictionPlanAppCrossRef(long planId, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName) {
        super();
    }
    
    public final long getPlanId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPackageName() {
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
    public final com.focuslock.data.AppRestrictionPlanAppCrossRef copy(long planId, @org.jetbrains.annotations.NotNull()
    java.lang.String packageName) {
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