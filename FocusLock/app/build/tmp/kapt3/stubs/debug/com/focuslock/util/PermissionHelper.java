package com.focuslock.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/focuslock/util/PermissionHelper;", "", "()V", "KEY_AUTO_START_ACK", "", "PREFS_NAME", "getMissingPermissions", "", "Lcom/focuslock/util/PermissionHelper$RequiredPermission;", "context", "Landroid/content/Context;", "hasAccessibilityPermission", "", "hasAutoStartConfirmed", "hasIgnoreBatteryOptimizations", "hasUsageStatsPermission", "markPermissionAcknowledged", "", "permission", "RequiredPermission", "app_debug"})
public final class PermissionHelper {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "focus_lock_prefs";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_AUTO_START_ACK = "auto_start_ack";
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
    
    private final boolean hasUsageStatsPermission(android.content.Context context) {
        return false;
    }
    
    private final boolean hasAccessibilityPermission(android.content.Context context) {
        return false;
    }
    
    private final boolean hasIgnoreBatteryOptimizations(android.content.Context context) {
        return false;
    }
    
    private final boolean hasAutoStartConfirmed(android.content.Context context) {
        return false;
    }
    
    public final void markPermissionAcknowledged(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.focuslock.util.PermissionHelper.RequiredPermission permission) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b\u00a8\u0006\f"}, d2 = {"Lcom/focuslock/util/PermissionHelper$RequiredPermission;", "", "(Ljava/lang/String;I)V", "settingsIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "OVERLAY", "USAGE_STATS", "ACCESSIBILITY", "BATTERY", "AUTO_START", "app_debug"})
    public static enum RequiredPermission {
        /*public static final*/ OVERLAY /* = new OVERLAY() */,
        /*public static final*/ USAGE_STATS /* = new USAGE_STATS() */,
        /*public static final*/ ACCESSIBILITY /* = new ACCESSIBILITY() */,
        /*public static final*/ BATTERY /* = new BATTERY() */,
        /*public static final*/ AUTO_START /* = new AUTO_START() */;
        
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