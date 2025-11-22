package com.focuslock;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000e\u001a\u00020\u000f8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\b\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0013\u001a\u00020\u00148FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\b\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0018\u001a\u00020\u00198FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\b\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006\u001d"}, d2 = {"Lcom/focuslock/FocusLockApplication;", "Landroid/app/Application;", "()V", "appRestrictionPlanRepository", "Lcom/focuslock/data/AppRestrictionPlanRepository;", "getAppRestrictionPlanRepository", "()Lcom/focuslock/data/AppRestrictionPlanRepository;", "appRestrictionPlanRepository$delegate", "Lkotlin/Lazy;", "lockScheduleDatabase", "Lcom/focuslock/data/LockScheduleDatabase;", "getLockScheduleDatabase", "()Lcom/focuslock/data/LockScheduleDatabase;", "lockScheduleDatabase$delegate", "lockScheduleRepository", "Lcom/focuslock/data/LockScheduleRepository;", "getLockScheduleRepository", "()Lcom/focuslock/data/LockScheduleRepository;", "lockScheduleRepository$delegate", "reminderRepository", "Lcom/focuslock/data/ReminderRepository;", "getReminderRepository", "()Lcom/focuslock/data/ReminderRepository;", "reminderRepository$delegate", "whitelistedAppRepository", "Lcom/focuslock/data/WhitelistedAppRepository;", "getWhitelistedAppRepository", "()Lcom/focuslock/data/WhitelistedAppRepository;", "whitelistedAppRepository$delegate", "app_debug"})
public final class FocusLockApplication extends android.app.Application {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy lockScheduleRepository$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy whitelistedAppRepository$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy appRestrictionPlanRepository$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy reminderRepository$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy lockScheduleDatabase$delegate = null;
    
    public FocusLockApplication() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.focuslock.data.LockScheduleRepository getLockScheduleRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.focuslock.data.WhitelistedAppRepository getWhitelistedAppRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.focuslock.data.AppRestrictionPlanRepository getAppRestrictionPlanRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.focuslock.data.ReminderRepository getReminderRepository() {
        return null;
    }
    
    private final com.focuslock.data.LockScheduleDatabase getLockScheduleDatabase() {
        return null;
    }
}