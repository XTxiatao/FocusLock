package com.focuslock.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0017\u001a\u00020\u0013H\u0002J$\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0013H\u0016J\b\u0010!\u001a\u00020\u0013H\u0016J\u001a\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u00192\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u0010\u0010$\u001a\u00020\u00132\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u0010\'\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J*\u0010(\u001a\u00020\u00132\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\b\u0010-\u001a\u0004\u0018\u00010\u0015H\u0002J\u0014\u0010.\u001a\u00020\u00132\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u0015H\u0002J\b\u0010/\u001a\u00020\u0013H\u0002J\b\u00100\u001a\u00020\u0013H\u0002J\u0010\u00101\u001a\u00020\u00132\u0006\u00102\u001a\u00020*H\u0002J\b\u00103\u001a\u00020\u0013H\u0002J\u0010\u00104\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f\u00a8\u00065"}, d2 = {"Lcom/focuslock/ui/DeviceLockFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/focuslock/databinding/FragmentDeviceLockBinding;", "adapter", "Lcom/focuslock/ui/ScheduleAdapter;", "binding", "getBinding", "()Lcom/focuslock/databinding/FragmentDeviceLockBinding;", "permissionDialog", "Landroid/app/AlertDialog;", "repository", "Lcom/focuslock/data/LockScheduleRepository;", "getRepository", "()Lcom/focuslock/data/LockScheduleRepository;", "repository$delegate", "Lkotlin/Lazy;", "confirmDeleteSchedule", "", "schedule", "Lcom/focuslock/model/LockSchedule;", "deleteSchedule", "evaluatePermissions", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onResume", "onViewCreated", "view", "promptForPermission", "permission", "Lcom/focuslock/util/PermissionHelper$RequiredPermission;", "requestEditSchedule", "saveLockPlan", "startMinutes", "", "endMinutes", "mask", "existing", "showAddPlanDialog", "showTemporaryLockDialog", "startLockService", "startTemporaryLock", "durationMinutes", "stopServiceIfNoActivePlans", "toggleSchedule", "app_release"})
public final class DeviceLockFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.focuslock.databinding.FragmentDeviceLockBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy repository$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final com.focuslock.ui.ScheduleAdapter adapter = null;
    @org.jetbrains.annotations.Nullable()
    private android.app.AlertDialog permissionDialog;
    
    public DeviceLockFragment() {
        super();
    }
    
    private final com.focuslock.databinding.FragmentDeviceLockBinding getBinding() {
        return null;
    }
    
    private final com.focuslock.data.LockScheduleRepository getRepository() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    private final void evaluatePermissions() {
    }
    
    private final void promptForPermission(com.focuslock.util.PermissionHelper.RequiredPermission permission) {
    }
    
    private final void saveLockPlan(int startMinutes, int endMinutes, int mask, com.focuslock.model.LockSchedule existing) {
    }
    
    private final void toggleSchedule(com.focuslock.model.LockSchedule schedule) {
    }
    
    private final void startLockService() {
    }
    
    private final void stopServiceIfNoActivePlans() {
    }
    
    private final void deleteSchedule(com.focuslock.model.LockSchedule schedule) {
    }
    
    private final void confirmDeleteSchedule(com.focuslock.model.LockSchedule schedule) {
    }
    
    private final void requestEditSchedule(com.focuslock.model.LockSchedule schedule) {
    }
    
    private final void showTemporaryLockDialog() {
    }
    
    private final void startTemporaryLock(int durationMinutes) {
    }
    
    private final void showAddPlanDialog(com.focuslock.model.LockSchedule existing) {
    }
}