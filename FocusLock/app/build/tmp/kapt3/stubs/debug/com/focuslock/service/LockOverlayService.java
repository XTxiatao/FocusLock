package com.focuslock.service;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00b6\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0018\u0018\u0000 `2\u00020\u0001:\u0003`abB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u001bH\u0002J\b\u00103\u001a\u000204H\u0002J\u000e\u00105\u001a\b\u0012\u0004\u0012\u0002060\bH\u0002J \u00107\u001a\u00020\u00062\u0006\u00108\u001a\u00020\u001b2\u0006\u00109\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020;H\u0002J\u001c\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010:\u001a\u00020;H\u0002J\b\u0010=\u001a\u000201H\u0002J\b\u0010>\u001a\u000201H\u0002J\u0010\u0010?\u001a\u00020\u00052\u0006\u0010@\u001a\u00020\u0006H\u0002J\u0010\u0010A\u001a\u00020\u00052\u0006\u0010B\u001a\u00020\u0006H\u0002J\u0012\u0010C\u001a\u0002012\b\u0010D\u001a\u0004\u0018\u00010EH\u0002J\b\u0010F\u001a\u000201H\u0002J\u0012\u0010G\u001a\u00020\u00152\b\u0010H\u001a\u0004\u0018\u00010\u0005H\u0002J\b\u0010I\u001a\u00020\u0015H\u0002J\u0014\u0010J\u001a\u0004\u0018\u00010K2\b\u0010D\u001a\u0004\u0018\u00010EH\u0016J\b\u0010L\u001a\u000201H\u0016J\b\u0010M\u001a\u000201H\u0016J\"\u0010N\u001a\u00020\u001b2\b\u0010D\u001a\u0004\u0018\u00010E2\u0006\u0010O\u001a\u00020\u001b2\u0006\u0010P\u001a\u00020\u001bH\u0016J\u0018\u0010Q\u001a\u00020\u00062\u0006\u0010R\u001a\u00020\u00122\u0006\u0010:\u001a\u00020;H\u0002J\b\u0010S\u001a\u000201H\u0002J\b\u0010T\u001a\u000201H\u0002J\b\u0010U\u001a\u000201H\u0002J\n\u0010V\u001a\u0004\u0018\u00010\u0005H\u0002J\u0010\u0010W\u001a\u0002012\u0006\u0010X\u001a\u00020\u0005H\u0002J\b\u0010Y\u001a\u000201H\u0002J\b\u0010Z\u001a\u000201H\u0002J\u0010\u0010[\u001a\u0002012\u0006\u0010\\\u001a\u00020\u0006H\u0002J\b\u0010]\u001a\u000201H\u0002J\b\u0010^\u001a\u000201H\u0002J\f\u0010_\u001a\u00020\u0005*\u00020\u0012H\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010&\u001a\b\u0018\u00010\'R\u00020\u0000X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006c"}, d2 = {"Lcom/focuslock/service/LockOverlayService;", "Landroid/app/Service;", "()V", "activeRestrictions", "", "", "", "appRestrictionPlans", "", "Lcom/focuslock/model/AppRestrictionPlan;", "binding", "Lcom/focuslock/databinding/OverlayViewBinding;", "channelId", "countdownHandler", "Landroid/os/Handler;", "countdownRunnable", "Ljava/lang/Runnable;", "currentSchedule", "Lcom/focuslock/model/LockSchedule;", "enabledSchedules", "evaluationRunning", "", "foregroundStarted", "lastForegroundPackage", "mainHandler", "nextUnlockTimeMillis", "notificationId", "", "overlayAdded", "repository", "Lcom/focuslock/data/LockScheduleRepository;", "restrictionPlanRepository", "Lcom/focuslock/data/AppRestrictionPlanRepository;", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "temporaryLockExpiryMillis", "whitelistDialog", "Landroidx/appcompat/app/AlertDialog;", "whitelistDialogAdapter", "Lcom/focuslock/service/LockOverlayService$WhitelistDialogAdapter;", "whitelistRepository", "Lcom/focuslock/data/WhitelistedAppRepository;", "whitelistedApps", "Lcom/focuslock/model/WhitelistedApp;", "windowManager", "Landroid/view/WindowManager;", "windowParams", "Landroid/view/WindowManager$LayoutParams;", "applyTemporaryLock", "", "durationMinutes", "buildNotification", "Landroid/app/Notification;", "buildWhitelistDialogItems", "Lcom/focuslock/service/LockOverlayService$WhitelistDialogItem;", "calculateEndMillis", "startMinutes", "endMinutes", "now", "Ljava/time/LocalDateTime;", "computeActiveRestrictions", "ensureChannel", "evaluateSchedule", "formatDuration", "remainingMillis", "formatUnlockClock", "unlockMillis", "handleTemporaryLockIntent", "intent", "Landroid/content/Intent;", "hideOverlay", "isPackageWhitelisted", "packageName", "isTemporaryLockActive", "onBind", "Landroid/os/IBinder;", "onCreate", "onDestroy", "onStartCommand", "flags", "startId", "planEndMillis", "schedule", "prepareOverlay", "promoteToForeground", "refreshWhitelistDialog", "resolveForegroundPackage", "setCountdownText", "text", "showOverlay", "showWhitelistAppDialog", "startCountdownFor", "targetMillis", "startEvaluationLoop", "stopCountdown", "debugDescription", "Companion", "WhitelistDialogAdapter", "WhitelistDialogItem", "app_debug"})
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
    private com.focuslock.service.LockOverlayService.WhitelistDialogAdapter whitelistDialogAdapter;
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0007H\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u0007H\u0016J\"\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00072\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0014\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/focuslock/service/LockOverlayService$WhitelistDialogAdapter;", "Landroid/widget/BaseAdapter;", "items", "", "Lcom/focuslock/service/LockOverlayService$WhitelistDialogItem;", "(Lcom/focuslock/service/LockOverlayService;Ljava/util/List;)V", "getCount", "", "getItem", "position", "getItemId", "", "getView", "Landroid/view/View;", "convertView", "parent", "Landroid/view/ViewGroup;", "update", "", "newItems", "app_debug"})
    final class WhitelistDialogAdapter extends android.widget.BaseAdapter {
        @org.jetbrains.annotations.NotNull()
        private java.util.List<com.focuslock.service.LockOverlayService.WhitelistDialogItem> items;
        
        public WhitelistDialogAdapter(@org.jetbrains.annotations.NotNull()
        java.util.List<com.focuslock.service.LockOverlayService.WhitelistDialogItem> items) {
            super();
        }
        
        @java.lang.Override()
        public int getCount() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public com.focuslock.service.LockOverlayService.WhitelistDialogItem getItem(int position) {
            return null;
        }
        
        @java.lang.Override()
        public long getItemId(int position) {
            return 0L;
        }
        
        public final void update(@org.jetbrains.annotations.NotNull()
        java.util.List<com.focuslock.service.LockOverlayService.WhitelistDialogItem> newItems) {
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public android.view.View getView(int position, @org.jetbrains.annotations.Nullable()
        android.view.View convertView, @org.jetbrains.annotations.NotNull()
        android.view.ViewGroup parent) {
            return null;
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