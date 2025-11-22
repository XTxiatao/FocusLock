package com.focuslock.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J$\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020\u0018H\u0016J\u001a\u0010\'\u001a\u00020\u00182\u0006\u0010(\u001a\u00020\u001f2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010)\u001a\u00020\u0018H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\u000e\u001a\u0010\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00100\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\r\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006*"}, d2 = {"Lcom/focuslock/ui/AppLockFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/focuslock/databinding/FragmentAppLockBinding;", "binding", "getBinding", "()Lcom/focuslock/databinding/FragmentAppLockBinding;", "displayAdapter", "Lcom/focuslock/ui/WhitelistDisplayAdapter;", "getDisplayAdapter", "()Lcom/focuslock/ui/WhitelistDisplayAdapter;", "displayAdapter$delegate", "Lkotlin/Lazy;", "pickAppLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "repository", "Lcom/focuslock/data/WhitelistedAppRepository;", "getRepository", "()Lcom/focuslock/data/WhitelistedAppRepository;", "repository$delegate", "confirmRemoveApp", "", "app", "Lcom/focuslock/model/WhitelistedApp;", "handleAppPicked", "result", "Landroidx/activity/result/ActivityResult;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "openSystemAppPicker", "app_debug"})
public final class AppLockFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.focuslock.databinding.FragmentAppLockBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy displayAdapter$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy repository$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> pickAppLauncher = null;
    
    public AppLockFragment() {
        super();
    }
    
    private final com.focuslock.databinding.FragmentAppLockBinding getBinding() {
        return null;
    }
    
    private final com.focuslock.ui.WhitelistDisplayAdapter getDisplayAdapter() {
        return null;
    }
    
    private final com.focuslock.data.WhitelistedAppRepository getRepository() {
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
    
    private final void openSystemAppPicker() {
    }
    
    private final void handleAppPicked(androidx.activity.result.ActivityResult result) {
    }
    
    private final void confirmRemoveApp(com.focuslock.model.WhitelistedApp app) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}