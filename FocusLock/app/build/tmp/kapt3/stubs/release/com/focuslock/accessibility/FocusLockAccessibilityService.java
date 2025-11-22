package com.focuslock.accessibility;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\f\u001a\u00020\tH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/focuslock/accessibility/FocusLockAccessibilityService;", "Landroid/accessibilityservice/AccessibilityService;", "()V", "lastHomeTriggerTime", "", "targetPackages", "", "", "onAccessibilityEvent", "", "event", "Landroid/view/accessibility/AccessibilityEvent;", "onInterrupt", "app_release"})
public final class FocusLockAccessibilityService extends android.accessibilityservice.AccessibilityService {
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.String> targetPackages = null;
    private long lastHomeTriggerTime = 0L;
    
    public FocusLockAccessibilityService() {
        super();
    }
    
    @java.lang.Override()
    public void onAccessibilityEvent(@org.jetbrains.annotations.Nullable()
    android.view.accessibility.AccessibilityEvent event) {
    }
    
    @java.lang.Override()
    public void onInterrupt() {
    }
}