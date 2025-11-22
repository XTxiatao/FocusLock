package com.focuslock.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\r\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u0010\u0007J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004R\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0005\u00a8\u0006\u000b"}, d2 = {"Lcom/focuslock/ui/ReminderNavigationEvents;", "", "()V", "pendingReminderId", "", "Ljava/lang/Long;", "consumeReminderEditRequest", "()Ljava/lang/Long;", "requestReminderEdit", "", "reminderId", "app_debug"})
public final class ReminderNavigationEvents {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile java.lang.Long pendingReminderId;
    @org.jetbrains.annotations.NotNull()
    public static final com.focuslock.ui.ReminderNavigationEvents INSTANCE = null;
    
    private ReminderNavigationEvents() {
        super();
    }
    
    public final void requestReminderEdit(long reminderId) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long consumeReminderEditRequest() {
        return null;
    }
}