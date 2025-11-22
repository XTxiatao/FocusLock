package com.focuslock.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002\u001a\b\u0010\u0007\u001a\u00020\u0003H\u0002\u001a4\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\t0\u0011H\u0002\u001a4\u0010\u0012\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\t0\u0011H\u0002\u001aD\u0010\u0013\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u001c\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"DATE_TIME_SUMMARY_FORMATTER", "Ljava/time/format/DateTimeFormatter;", "encodeWeekDays", "", "days", "", "Ljava/time/DayOfWeek;", "overlayWindowType", "pickDateTime", "", "context", "Landroid/content/Context;", "initial", "Ljava/time/ZonedDateTime;", "overlayWindow", "", "onResult", "Lkotlin/Function1;", "pickTimeWithWheel", "showReminderEditorDialog", "Landroidx/appcompat/app/AlertDialog;", "reminder", "Lcom/focuslock/model/Reminder;", "repository", "Lcom/focuslock/data/ReminderRepository;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "onDismiss", "Lkotlin/Function0;", "app_debug"})
public final class ReminderEditorDialogKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.time.format.DateTimeFormatter DATE_TIME_SUMMARY_FORMATTER = null;
    
    @org.jetbrains.annotations.NotNull()
    public static final androidx.appcompat.app.AlertDialog showReminderEditorDialog(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    com.focuslock.model.Reminder reminder, @org.jetbrains.annotations.NotNull()
    com.focuslock.data.ReminderRepository repository, @org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope scope, boolean overlayWindow, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
        return null;
    }
    
    private static final int encodeWeekDays(java.util.Set<? extends java.time.DayOfWeek> days) {
        return 0;
    }
    
    private static final void pickDateTime(android.content.Context context, java.time.ZonedDateTime initial, boolean overlayWindow, kotlin.jvm.functions.Function1<? super java.time.ZonedDateTime, kotlin.Unit> onResult) {
    }
    
    private static final void pickTimeWithWheel(android.content.Context context, java.time.ZonedDateTime initial, boolean overlayWindow, kotlin.jvm.functions.Function1<? super java.time.ZonedDateTime, kotlin.Unit> onResult) {
    }
    
    private static final int overlayWindowType() {
        return 0;
    }
}