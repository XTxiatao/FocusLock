package com.focuslock.service;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 D2\u00020\u0001:\u0001DB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0016H\u0002J\b\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020\"H\u0002J\b\u0010\'\u001a\u00020\"H\u0002J\u0010\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u0014H\u0002J\u0012\u0010*\u001a\u00020\"2\b\u0010+\u001a\u0004\u0018\u00010,H\u0002J\b\u0010-\u001a\u00020\"H\u0002J\b\u0010.\u001a\u00020\u0010H\u0002J\u0014\u0010/\u001a\u0004\u0018\u0001002\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J\b\u00101\u001a\u00020\"H\u0016J\b\u00102\u001a\u00020\"H\u0016J\"\u00103\u001a\u00020\u00162\b\u0010+\u001a\u0004\u0018\u00010,2\u0006\u00104\u001a\u00020\u00162\u0006\u00105\u001a\u00020\u0016H\u0016J\u0018\u00106\u001a\u00020\u00142\u0006\u00107\u001a\u00020\f2\u0006\u00108\u001a\u000209H\u0002J\b\u0010:\u001a\u00020\"H\u0002J\b\u0010;\u001a\u00020\"H\u0002J\u0010\u0010<\u001a\u00020\"2\u0006\u0010=\u001a\u00020\u0006H\u0002J\b\u0010>\u001a\u00020\"H\u0002J\u0010\u0010?\u001a\u00020\"2\u0006\u0010@\u001a\u00020\u0014H\u0002J\b\u0010A\u001a\u00020\"H\u0002J\b\u0010B\u001a\u00020\"H\u0002J\f\u0010C\u001a\u00020\u0006*\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006E"}, d2 = {"Lcom/focuslock/service/LockOverlayService;", "Landroid/app/Service;", "()V", "binding", "Lcom/focuslock/databinding/OverlayViewBinding;", "channelId", "", "countdownHandler", "Landroid/os/Handler;", "countdownRunnable", "Ljava/lang/Runnable;", "currentSchedule", "Lcom/focuslock/model/LockSchedule;", "enabledSchedules", "", "evaluationRunning", "", "foregroundStarted", "mainHandler", "nextUnlockTimeMillis", "", "notificationId", "", "overlayAdded", "repository", "Lcom/focuslock/data/LockScheduleRepository;", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "temporaryLockExpiryMillis", "windowManager", "Landroid/view/WindowManager;", "windowParams", "Landroid/view/WindowManager$LayoutParams;", "applyTemporaryLock", "", "durationMinutes", "buildNotification", "Landroid/app/Notification;", "ensureChannel", "evaluateSchedule", "formatDuration", "remainingMillis", "handleTemporaryLockIntent", "intent", "Landroid/content/Intent;", "hideOverlay", "isTemporaryLockActive", "onBind", "Landroid/os/IBinder;", "onCreate", "onDestroy", "onStartCommand", "flags", "startId", "planEndMillis", "schedule", "now", "Ljava/time/LocalDateTime;", "prepareOverlay", "promoteToForeground", "setCountdownText", "text", "showOverlay", "startCountdownFor", "targetMillis", "startEvaluationLoop", "stopCountdown", "debugDescription", "Companion", "app_debug"})
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
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.focuslock.model.LockSchedule> enabledSchedules;
    private android.view.WindowManager.LayoutParams windowParams;
    private boolean foregroundStarted = false;
    private long temporaryLockExpiryMillis = 0L;
    private boolean evaluationRunning = false;
    private long nextUnlockTimeMillis = 0L;
    @org.jetbrains.annotations.Nullable()
    private com.focuslock.model.LockSchedule currentSchedule;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_TEMP_LOCK_MINUTES = "extra_temp_lock_minutes";
    @org.jetbrains.annotations.NotNull()
    public static final com.focuslock.service.LockOverlayService.Companion Companion = null;
    
    public LockOverlayService() {
        super();
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
    
    private final void startCountdownFor(long targetMillis) {
    }
    
    private final void stopCountdown() {
    }
    
    private final java.lang.String formatDuration(long remainingMillis) {
        return null;
    }
    
    private final long planEndMillis(com.focuslock.model.LockSchedule schedule, java.time.LocalDateTime now) {
        return 0L;
    }
    
    private final void handleTemporaryLockIntent(android.content.Intent intent) {
    }
    
    private final void applyTemporaryLock(int durationMinutes) {
    }
    
    private final void setCountdownText(java.lang.String text) {
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
    
    private final java.lang.String debugDescription(com.focuslock.model.LockSchedule $this$debugDescription) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/focuslock/service/LockOverlayService$Companion;", "", "()V", "EXTRA_TEMP_LOCK_MINUTES", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}