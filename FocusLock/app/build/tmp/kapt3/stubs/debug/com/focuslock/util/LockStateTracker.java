package com.focuslock.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/focuslock/util/LockStateTracker;", "", "()V", "currentForegroundPackage", "", "getCurrentForegroundPackage", "()Ljava/lang/String;", "setCurrentForegroundPackage", "(Ljava/lang/String;)V", "enforceHome", "", "getEnforceHome", "()Z", "setEnforceHome", "(Z)V", "app_debug"})
public final class LockStateTracker {
    @kotlin.jvm.Volatile()
    private static volatile boolean enforceHome = false;
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile java.lang.String currentForegroundPackage;
    @org.jetbrains.annotations.NotNull()
    public static final com.focuslock.util.LockStateTracker INSTANCE = null;
    
    private LockStateTracker() {
        super();
    }
    
    public final boolean getEnforceHome() {
        return false;
    }
    
    public final void setEnforceHome(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCurrentForegroundPackage() {
        return null;
    }
    
    public final void setCurrentForegroundPackage(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
}