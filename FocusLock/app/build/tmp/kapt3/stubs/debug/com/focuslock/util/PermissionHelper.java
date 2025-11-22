package com.focuslock.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\fB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u000e\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007\u00a8\u0006\r"}, d2 = {"Lcom/focuslock/util/PermissionHelper;", "", "()V", "getMissingPermissions", "", "Lcom/focuslock/util/PermissionHelper$RequiredPermission;", "context", "Landroid/content/Context;", "hasAccessibilityPermission", "", "hasIgnoreBatteryOptimizations", "hasUsageStatsPermission", "RequiredPermission", "app_debug"})
public final class PermissionHelper {
    @org.jetbrains.annotations.NotNull()
    public static final com.focuslock.util.PermissionHelper INSTANCE = null;
    
    private PermissionHelper() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.focuslock.util.PermissionHelper.RequiredPermission> getMissingPermissions(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    public final boolean hasUsageStatsPermission(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    private final boolean hasAccessibilityPermission(android.content.Context context) {
        return false;
    }
    
    private final boolean hasIgnoreBatteryOptimizations(android.content.Context context) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n\u00a8\u0006\u000b"}, d2 = {"Lcom/focuslock/util/PermissionHelper$RequiredPermission;", "", "(Ljava/lang/String;I)V", "settingsIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "OVERLAY", "USAGE_STATS", "ACCESSIBILITY", "BATTERY", "app_debug"})
    public static enum RequiredPermission {
        /*public static final*/ OVERLAY /* = new OVERLAY() */,
        /*public static final*/ USAGE_STATS /* = new USAGE_STATS() */,
        /*public static final*/ ACCESSIBILITY /* = new ACCESSIBILITY() */,
        /*public static final*/ BATTERY /* = new BATTERY() */;
        
        RequiredPermission() {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final android.content.Intent settingsIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.focuslock.util.PermissionHelper.RequiredPermission> getEntries() {
            return null;
        }
    }
}