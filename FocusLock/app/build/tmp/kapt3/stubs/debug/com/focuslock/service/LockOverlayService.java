package com.focuslock.service;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 M2\u00020\u0001:\u0002MNB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\u0017H\u0002J\b\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020\'H\u0002J\b\u0010,\u001a\u00020\'H\u0002J\u0010\u0010-\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u0015H\u0002J\u0012\u0010/\u001a\u00020\'2\b\u00100\u001a\u0004\u0018\u000101H\u0002J\b\u00102\u001a\u00020\'H\u0002J\u0012\u00103\u001a\u00020\u00102\b\u00104\u001a\u0004\u0018\u00010\u0006H\u0002J\b\u00105\u001a\u00020\u0010H\u0002J\u0014\u00106\u001a\u0004\u0018\u0001072\b\u00100\u001a\u0004\u0018\u000101H\u0016J\b\u00108\u001a\u00020\'H\u0016J\b\u00109\u001a\u00020\'H\u0016J\"\u0010:\u001a\u00020\u00172\b\u00100\u001a\u0004\u0018\u0001012\u0006\u0010;\u001a\u00020\u00172\u0006\u0010<\u001a\u00020\u0017H\u0016J\u0018\u0010=\u001a\u00020\u00152\u0006\u0010>\u001a\u00020\f2\u0006\u0010?\u001a\u00020@H\u0002J\b\u0010A\u001a\u00020\'H\u0002J\b\u0010B\u001a\u00020\'H\u0002J\n\u0010C\u001a\u0004\u0018\u00010\u0006H\u0002J\u0010\u0010D\u001a\u00020\'2\u0006\u0010E\u001a\u00020\u0006H\u0002J\b\u0010F\u001a\u00020\'H\u0002J\b\u0010G\u001a\u00020\'H\u0002J\u0010\u0010H\u001a\u00020\'2\u0006\u0010I\u001a\u00020\u0015H\u0002J\b\u0010J\u001a\u00020\'H\u0002J\b\u0010K\u001a\u00020\'H\u0002J\f\u0010L\u001a\u00020\u0006*\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006O"}, d2 = {"Lcom/focuslock/service/LockOverlayService;", "Landroid/app/Service;", "()V", "binding", "Lcom/focuslock/databinding/OverlayViewBinding;", "channelId", "", "countdownHandler", "Landroid/os/Handler;", "countdownRunnable", "Ljava/lang/Runnable;", "currentSchedule", "Lcom/focuslock/model/LockSchedule;", "enabledSchedules", "", "evaluationRunning", "", "foregroundStarted", "lastForegroundPackage", "mainHandler", "nextUnlockTimeMillis", "", "notificationId", "", "overlayAdded", "repository", "Lcom/focuslock/data/LockScheduleRepository;", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "temporaryLockExpiryMillis", "whitelistRepository", "Lcom/focuslock/data/WhitelistedAppRepository;", "whitelistedApps", "Lcom/focuslock/model/WhitelistedApp;", "windowManager", "Landroid/view/WindowManager;", "windowParams", "Landroid/view/WindowManager$LayoutParams;", "applyTemporaryLock", "", "durationMinutes", "buildNotification", "Landroid/app/Notification;", "ensureChannel", "evaluateSchedule", "formatDuration", "remainingMillis", "handleTemporaryLockIntent", "intent", "Landroid/content/Intent;", "hideOverlay", "isPackageWhitelisted", "packageName", "isTemporaryLockActive", "onBind", "Landroid/os/IBinder;", "onCreate", "onDestroy", "onStartCommand", "flags", "startId", "planEndMillis", "schedule", "now", "Ljava/time/LocalDateTime;", "prepareOverlay", "promoteToForeground", "resolveForegroundPackage", "setCountdownText", "text", "showOverlay", "showWhitelistAppDialog", "startCountdownFor", "targetMillis", "startEvaluationLoop", "stopCountdown", "debugDescription", "Companion", "WhitelistDialogAdapter", "app_debug"})
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
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.focuslock.model.LockSchedule> enabledSchedules;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.focuslock.model.WhitelistedApp> whitelistedApps;
    private android.view.WindowManager.LayoutParams windowParams;
    private boolean foregroundStarted = false;
    private long temporaryLockExpiryMillis = 0L;
    private boolean evaluationRunning = false;
    private long nextUnlockTimeMillis = 0L;
    @org.jetbrains.annotations.Nullable()
    private com.focuslock.model.LockSchedule currentSchedule;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String lastForegroundPackage;
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
    
    private final void showWhitelistAppDialog() {
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
    
    private final java.lang.String resolveForegroundPackage() {
        return null;
    }
    
    private final boolean isPackageWhitelisted(java.lang.String packageName) {
        return false;
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0007H\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u0007H\u0016J\"\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/focuslock/service/LockOverlayService$WhitelistDialogAdapter;", "Landroid/widget/BaseAdapter;", "apps", "", "Lcom/focuslock/model/WhitelistedApp;", "(Lcom/focuslock/service/LockOverlayService;Ljava/util/List;)V", "getCount", "", "getItem", "position", "getItemId", "", "getView", "Landroid/view/View;", "convertView", "parent", "Landroid/view/ViewGroup;", "app_debug"})
    final class WhitelistDialogAdapter extends android.widget.BaseAdapter {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<com.focuslock.model.WhitelistedApp> apps = null;
        
        public WhitelistDialogAdapter(@org.jetbrains.annotations.NotNull()
        java.util.List<com.focuslock.model.WhitelistedApp> apps) {
            super();
        }
        
        @java.lang.Override()
        public int getCount() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public com.focuslock.model.WhitelistedApp getItem(int position) {
            return null;
        }
        
        @java.lang.Override()
        public long getItemId(int position) {
            return 0L;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public android.view.View getView(int position, @org.jetbrains.annotations.Nullable()
        android.view.View convertView, @org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent) {
            return null;
        }
    }
}