package com.focuslock.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0012\u0010\u0018\u001a\u00020\u00102\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0010H\u0014J\b\u0010\u001c\u001a\u00020\u0010H\u0014J\u0010\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J \u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0017H\u0002J\b\u0010$\u001a\u00020\u0010H\u0002J\b\u0010%\u001a\u00020\u0010H\u0002J$\u0010&\u001a\u00020\u00102\u0006\u0010\'\u001a\u00020\u00172\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00100)H\u0002J\b\u0010*\u001a\u00020\u0010H\u0002J\u0010\u0010+\u001a\u00020\u00102\u0006\u0010,\u001a\u00020\u0017H\u0002J\b\u0010-\u001a\u00020\u0010H\u0002J\u0010\u0010.\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f\u00a8\u0006/"}, d2 = {"Lcom/focuslock/ui/MainActivity;", "Landroidx/activity/ComponentActivity;", "()V", "adapter", "Lcom/focuslock/ui/ScheduleAdapter;", "binding", "Lcom/focuslock/databinding/ActivityMainBinding;", "permissionDialog", "Landroid/app/AlertDialog;", "repository", "Lcom/focuslock/data/LockScheduleRepository;", "getRepository", "()Lcom/focuslock/data/LockScheduleRepository;", "repository$delegate", "Lkotlin/Lazy;", "deleteSchedule", "", "schedule", "Lcom/focuslock/model/LockSchedule;", "evaluatePermissions", "formatTimeLabel", "", "minutes", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "promptForPermission", "permission", "Lcom/focuslock/util/PermissionHelper$RequiredPermission;", "saveLockPlan", "startMinutes", "endMinutes", "mask", "showAddPlanDialog", "showTemporaryLockDialog", "showTimePickerDialog", "initialMinutes", "onSelected", "Lkotlin/Function1;", "startLockService", "startTemporaryLock", "durationMinutes", "stopServiceIfNoActivePlans", "toggleSchedule", "app_debug"})
public final class MainActivity extends androidx.activity.ComponentActivity {
    private com.focuslock.databinding.ActivityMainBinding binding;
    @org.jetbrains.annotations.Nullable()
    private android.app.AlertDialog permissionDialog;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy repository$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final com.focuslock.ui.ScheduleAdapter adapter = null;
    
    public MainActivity() {
        super();
    }
    
    private final com.focuslock.data.LockScheduleRepository getRepository() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final void evaluatePermissions() {
    }
    
    private final void promptForPermission(com.focuslock.util.PermissionHelper.RequiredPermission permission) {
    }
    
    private final void saveLockPlan(int startMinutes, int endMinutes, int mask) {
    }
    
    private final void toggleSchedule(com.focuslock.model.LockSchedule schedule) {
    }
    
    private final void startLockService() {
    }
    
    private final void stopServiceIfNoActivePlans() {
    }
    
    private final void deleteSchedule(com.focuslock.model.LockSchedule schedule) {
    }
    
    private final void showTemporaryLockDialog() {
    }
    
    private final void startTemporaryLock(int durationMinutes) {
    }
    
    private final void showAddPlanDialog() {
    }
    
    private final void showTimePickerDialog(int initialMinutes, kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onSelected) {
    }
    
    private final java.lang.String formatTimeLabel(int minutes) {
        return null;
    }
}