package com.focuslock.service;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00ec\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\r\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u007f2\u00020\u0001:\u0005\u007f\u0080\u0001\u0081\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020\u001bH\u0002J\b\u0010E\u001a\u00020FH\u0002J\u000e\u0010G\u001a\b\u0012\u0004\u0012\u00020H0\bH\u0002J \u0010I\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\u001b2\u0006\u0010K\u001a\u00020\u001b2\u0006\u0010L\u001a\u00020MH\u0002J\u0010\u0010N\u001a\u00020C2\u0006\u0010O\u001a\u00020PH\u0002J\u001c\u0010Q\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010L\u001a\u00020MH\u0002J\b\u0010R\u001a\u00020\u001bH\u0002J\b\u0010S\u001a\u00020CH\u0002J\b\u0010T\u001a\u00020CH\u0002J\u0010\u0010U\u001a\u00020\u00052\u0006\u0010V\u001a\u00020\u0006H\u0002J\u0010\u0010W\u001a\u00020\u00052\u0006\u0010X\u001a\u00020\u0006H\u0002J\u0012\u0010Y\u001a\u00020C2\b\u0010Z\u001a\u0004\u0018\u00010[H\u0002J\b\u0010\\\u001a\u00020CH\u0002J\b\u0010]\u001a\u00020\u0015H\u0002J\u0012\u0010^\u001a\u00020\u00152\b\u0010_\u001a\u0004\u0018\u00010\u0005H\u0002J\b\u0010`\u001a\u00020\u0015H\u0002J\u0014\u0010a\u001a\u0004\u0018\u00010b2\b\u0010Z\u001a\u0004\u0018\u00010[H\u0016J\b\u0010c\u001a\u00020CH\u0016J\b\u0010d\u001a\u00020CH\u0016J\"\u0010e\u001a\u00020\u001b2\b\u0010Z\u001a\u0004\u0018\u00010[2\u0006\u0010f\u001a\u00020\u001b2\u0006\u0010g\u001a\u00020\u001bH\u0016J\u0014\u0010h\u001a\u00020C2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010PH\u0002J\b\u0010i\u001a\u00020CH\u0002J\u0018\u0010j\u001a\u00020\u00062\u0006\u0010k\u001a\u00020\u00122\u0006\u0010L\u001a\u00020MH\u0002J\b\u0010l\u001a\u00020CH\u0002J\b\u0010m\u001a\u00020CH\u0002J\b\u0010n\u001a\u00020CH\u0002J\n\u0010o\u001a\u0004\u0018\u00010\u0005H\u0002J\u0010\u0010p\u001a\u00020C2\u0006\u0010q\u001a\u00020\u0005H\u0002J\b\u0010r\u001a\u00020CH\u0002J\b\u0010s\u001a\u00020CH\u0002J\u0010\u0010t\u001a\u00020C2\u0006\u0010u\u001a\u00020vH\u0002J\b\u0010w\u001a\u00020CH\u0002J\u0010\u0010x\u001a\u00020C2\u0006\u0010y\u001a\u00020\u0006H\u0002J\b\u0010z\u001a\u00020CH\u0002J\b\u0010{\u001a\u00020CH\u0002J\f\u0010|\u001a\u00020C*\u00020}H\u0002J\f\u0010~\u001a\u00020\u0005*\u00020\u0012H\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0018\u00010!R\u00020\u0000X\u0082\u000e\u00a2\u0006\u0002\n\u0000R#\u0010\"\u001a\n $*\u0004\u0018\u00010#0#8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\'\u0010(\u001a\u0004\b%\u0010&R\u001b\u0010)\u001a\u00020*8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b-\u0010(\u001a\u0004\b+\u0010,R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000205X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u00109\u001a\u0004\u0018\u00010/X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020;X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010<\u001a\b\u0012\u0004\u0012\u00020=0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020?X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020AX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0082\u0001"}, d2 = {"Lcom/focuslock/service/LockOverlayService;", "Landroid/app/Service;", "()V", "activeRestrictions", "", "", "", "appRestrictionPlans", "", "Lcom/focuslock/model/AppRestrictionPlan;", "binding", "Lcom/focuslock/databinding/OverlayViewBinding;", "channelId", "countdownHandler", "Landroid/os/Handler;", "countdownRunnable", "Ljava/lang/Runnable;", "currentSchedule", "Lcom/focuslock/model/LockSchedule;", "enabledSchedules", "evaluationRunning", "", "foregroundStarted", "lastForegroundPackage", "mainHandler", "nextUnlockTimeMillis", "notificationId", "", "overlayAdded", "overlayContext", "Landroid/content/Context;", "overlayResumeAfterMillis", "overlayWhitelistAdapter", "Lcom/focuslock/service/LockOverlayService$OverlayWhitelistAdapter;", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "getPrefs", "()Landroid/content/SharedPreferences;", "prefs$delegate", "Lkotlin/Lazy;", "reminderAdapter", "Lcom/focuslock/ui/ReminderDayAdapter;", "getReminderAdapter", "()Lcom/focuslock/ui/ReminderDayAdapter;", "reminderAdapter$delegate", "reminderEditorDialog", "Landroidx/appcompat/app/AlertDialog;", "reminderRepository", "Lcom/focuslock/data/ReminderRepository;", "repository", "Lcom/focuslock/data/LockScheduleRepository;", "restrictionPlanRepository", "Lcom/focuslock/data/AppRestrictionPlanRepository;", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "temporaryLockExpiryMillis", "whitelistDialog", "whitelistRepository", "Lcom/focuslock/data/WhitelistedAppRepository;", "whitelistedApps", "Lcom/focuslock/model/WhitelistedApp;", "windowManager", "Landroid/view/WindowManager;", "windowParams", "Landroid/view/WindowManager$LayoutParams;", "applyTemporaryLock", "", "durationMinutes", "buildNotification", "Landroid/app/Notification;", "buildWhitelistDialogItems", "Lcom/focuslock/service/LockOverlayService$WhitelistDialogItem;", "calculateEndMillis", "startMinutes", "endMinutes", "now", "Ljava/time/LocalDateTime;", "completeReminderFromOverlay", "reminder", "Lcom/focuslock/model/Reminder;", "computeActiveRestrictions", "dialogWindowType", "ensureChannel", "evaluateSchedule", "formatDuration", "remainingMillis", "formatUnlockClock", "unlockMillis", "handleTemporaryLockIntent", "intent", "Landroid/content/Intent;", "hideOverlay", "isForceUnlockAllowed", "isPackageWhitelisted", "packageName", "isTemporaryLockActive", "onBind", "Landroid/os/IBinder;", "onCreate", "onDestroy", "onStartCommand", "flags", "startId", "openReminderEditorFromOverlay", "performForceUnlock", "planEndMillis", "schedule", "prepareOverlay", "promoteToForeground", "refreshWhitelistDialog", "resolveForegroundPackage", "setCountdownText", "text", "showForceUnlockDialog", "showOverlay", "showOverlayAlert", "message", "", "showWhitelistAppDialog", "startCountdownFor", "targetMillis", "startEvaluationLoop", "stopCountdown", "configureResponsiveGrid", "Landroidx/recyclerview/widget/RecyclerView;", "debugDescription", "Companion", "OverlayWhitelistAdapter", "WhitelistDialogItem", "app_debug"})
public final class LockOverlayService extends android.app.Service {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String channelId = "focus_lock_channel";
    private final int notificationId = 101;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    @org.jetbrains.annotations.NotNull()
    private final android.os.Handler mainHandler = null;
    @org.jetbrains.annotations.NotNull()
    private final android.os.Handler countdownHandler = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Runnable countdownRunnable;
    private boolean overlayAdded = false;
    private android.view.WindowManager windowManager;
    private com.focuslock.databinding.OverlayViewBinding binding;
    private com.focuslock.data.LockScheduleRepository repository;
    private com.focuslock.data.WhitelistedAppRepository whitelistRepository;
    private com.focuslock.data.AppRestrictionPlanRepository restrictionPlanRepository;
    private com.focuslock.data.ReminderRepository reminderRepository;
    private android.content.Context overlayContext;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.focuslock.model.LockSchedule> enabledSchedules;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.focuslock.model.WhitelistedApp> whitelistedApps;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.focuslock.model.AppRestrictionPlan> appRestrictionPlans;
    private android.view.WindowManager.LayoutParams windowParams;
    private boolean foregroundStarted = false;
    private long temporaryLockExpiryMillis = 0L;
    private boolean evaluationRunning = false;
    private long nextUnlockTimeMillis = 0L;
    @org.jetbrains.annotations.Nullable()
    private com.focuslock.model.LockSchedule currentSchedule;
    @org.jetbrains.annotations.NotNull()
    private java.util.Map<java.lang.String, java.lang.Long> activeRestrictions;
    @org.jetbrains.annotations.Nullable()
    private androidx.appcompat.app.AlertDialog whitelistDialog;
    @org.jetbrains.annotations.Nullable()
    private com.focuslock.service.LockOverlayService.OverlayWhitelistAdapter overlayWhitelistAdapter;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String lastForegroundPackage;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy reminderAdapter$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private androidx.appcompat.app.AlertDialog reminderEditorDialog;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy prefs$delegate = null;
    private long overlayResumeAfterMillis = 0L;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_TEMP_LOCK_MINUTES = "extra_temp_lock_minutes";
    @org.jetbrains.annotations.NotNull()
    public static final com.focuslock.service.LockOverlayService.Companion Companion = null;
    
    public LockOverlayService() {
        super();
    }
    
    private final com.focuslock.ui.ReminderDayAdapter getReminderAdapter() {
        return null;
    }
    
    private final android.content.SharedPreferences getPrefs() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    private final void prepareOverlay() {
    }
    
    private final void evaluateSchedule() {
    }
    
    private final void showOverlay() {
    }
    
    private final void hideOverlay() {
    }
    
    private final void startEvaluationLoop() {
    }
    
    private final void showWhitelistAppDialog() {
    }
    
    private final void showForceUnlockDialog() {
    }
    
    private final boolean isForceUnlockAllowed() {
        return false;
    }
    
    private final void performForceUnlock() {
    }
    
    private final void refreshWhitelistDialog() {
    }
    
    private final java.util.List<com.focuslock.service.LockOverlayService.WhitelistDialogItem> buildWhitelistDialogItems() {
        return null;
    }
    
    private final void startCountdownFor(long targetMillis) {
    }
    
    private final void stopCountdown() {
    }
    
    private final java.lang.String formatDuration(long remainingMillis) {
        return null;
    }
    
    private final java.lang.String formatUnlockClock(long unlockMillis) {
        return null;
    }
    
    private final long planEndMillis(com.focuslock.model.LockSchedule schedule, java.time.LocalDateTime now) {
        return 0L;
    }
    
    private final long calculateEndMillis(int startMinutes, int endMinutes, java.time.LocalDateTime now) {
        return 0L;
    }
    
    private final java.util.Map<java.lang.String, java.lang.Long> computeActiveRestrictions(java.time.LocalDateTime now) {
        return null;
    }
    
    private final void handleTemporaryLockIntent(android.content.Intent intent) {
    }
    
    private final void applyTemporaryLock(int durationMinutes) {
    }
    
    private final void setCountdownText(java.lang.String text) {
    }
    
    private final java.lang.String resolveForegroundPackage() {
        return null;
    }
    
    private final boolean isPackageWhitelisted(java.lang.String packageName) {
        return false;
    }
    
    private final void configureResponsiveGrid(androidx.recyclerview.widget.RecyclerView $this$configureResponsiveGrid) {
    }
    
    private final boolean isTemporaryLockActive() {
        return false;
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    private final android.app.Notification buildNotification() {
        return null;
    }
    
    private final void ensureChannel() {
    }
    
    private final void promoteToForeground() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    private final int dialogWindowType() {
        return 0;
    }
    
    private final void openReminderEditorFromOverlay(com.focuslock.model.Reminder reminder) {
    }
    
    private final void showOverlayAlert(java.lang.CharSequence message) {
    }
    
    private final void completeReminderFromOverlay(com.focuslock.model.Reminder reminder) {
    }
    
    private final java.lang.String debugDescription(com.focuslock.model.LockSchedule $this$debugDescription) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/focuslock/service/LockOverlayService$Companion;", "", "()V", "EXTRA_TEMP_LOCK_MINUTES", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u0010\u0012\f\u0012\n0\u0002R\u00060\u0000R\u00020\u00030\u0001:\u0001\u0017B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0016J \u0010\r\u001a\u00020\u00072\u000e\u0010\u000e\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\u000f\u001a\u00020\fH\u0016J \u0010\u0010\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\fH\u0016J\u0014\u0010\u0014\u001a\u00020\u00072\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/focuslock/service/LockOverlayService$OverlayWhitelistAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/focuslock/service/LockOverlayService$OverlayWhitelistAdapter$ViewHolder;", "Lcom/focuslock/service/LockOverlayService;", "clickListener", "Lkotlin/Function1;", "Lcom/focuslock/service/LockOverlayService$WhitelistDialogItem;", "", "(Lcom/focuslock/service/LockOverlayService;Lkotlin/jvm/functions/Function1;)V", "items", "", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "submitList", "list", "", "ViewHolder", "app_debug"})
    final class OverlayWhitelistAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.focuslock.service.LockOverlayService.OverlayWhitelistAdapter.ViewHolder> {
        @org.jetbrains.annotations.NotNull()
        private final kotlin.jvm.functions.Function1<com.focuslock.service.LockOverlayService.WhitelistDialogItem, kotlin.Unit> clickListener = null;
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.focuslock.service.LockOverlayService.WhitelistDialogItem> items = null;
        
        public OverlayWhitelistAdapter(@org.jetbrains.annotations.NotNull()
        kotlin.jvm.functions.Function1<? super com.focuslock.service.LockOverlayService.WhitelistDialogItem, kotlin.Unit> clickListener) {
            super();
        }
        
        public final void submitList(@org.jetbrains.annotations.NotNull()
        java.util.List<com.focuslock.service.LockOverlayService.WhitelistDialogItem> list) {
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public com.focuslock.service.LockOverlayService.OverlayWhitelistAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent, int viewType) {
            return null;
        }
        
        @java.lang.Override()
        public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
        com.focuslock.service.LockOverlayService.OverlayWhitelistAdapter.ViewHolder holder, int position) {
        }
        
        @java.lang.Override()
        public int getItemCount() {
            return 0;
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/focuslock/service/LockOverlayService$OverlayWhitelistAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/focuslock/databinding/ItemWhitelistDialogBinding;", "(Lcom/focuslock/service/LockOverlayService$OverlayWhitelistAdapter;Lcom/focuslock/databinding/ItemWhitelistDialogBinding;)V", "bind", "", "item", "Lcom/focuslock/service/LockOverlayService$WhitelistDialogItem;", "app_debug"})
        public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
            @org.jetbrains.annotations.NotNull()
            private final com.focuslock.databinding.ItemWhitelistDialogBinding binding = null;
            
            public ViewHolder(@org.jetbrains.annotations.NotNull()
            com.focuslock.databinding.ItemWhitelistDialogBinding binding) {
                super(null);
            }
            
            public final void bind(@org.jetbrains.annotations.NotNull()
            com.focuslock.service.LockOverlayService.WhitelistDialogItem item) {
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\nJ$\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001J\u0010\u0010\u0015\u001a\u00020\u00112\b\b\u0002\u0010\u0016\u001a\u00020\u0005J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0019"}, d2 = {"Lcom/focuslock/service/LockOverlayService$WhitelistDialogItem;", "", "app", "Lcom/focuslock/model/WhitelistedApp;", "restrictedUntil", "", "(Lcom/focuslock/model/WhitelistedApp;Ljava/lang/Long;)V", "getApp", "()Lcom/focuslock/model/WhitelistedApp;", "getRestrictedUntil", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "copy", "(Lcom/focuslock/model/WhitelistedApp;Ljava/lang/Long;)Lcom/focuslock/service/LockOverlayService$WhitelistDialogItem;", "equals", "", "other", "hashCode", "", "isRestricted", "now", "toString", "", "app_debug"})
    static final class WhitelistDialogItem {
        @org.jetbrains.annotations.NotNull()
        private final com.focuslock.model.WhitelistedApp app = null;
        @org.jetbrains.annotations.Nullable()
        private final java.lang.Long restrictedUntil = null;
        
        public WhitelistDialogItem(@org.jetbrains.annotations.NotNull()
        com.focuslock.model.WhitelistedApp app, @org.jetbrains.annotations.Nullable()
        java.lang.Long restrictedUntil) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.focuslock.model.WhitelistedApp getApp() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long getRestrictedUntil() {
            return null;
        }
        
        public final boolean isRestricted(long now) {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.focuslock.model.WhitelistedApp component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Long component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.focuslock.service.LockOverlayService.WhitelistDialogItem copy(@org.jetbrains.annotations.NotNull()
        com.focuslock.model.WhitelistedApp app, @org.jetbrains.annotations.Nullable()
        java.lang.Long restrictedUntil) {
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
}