package com.focuslock.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eJ \u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0086@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0018"}, d2 = {"Lcom/focuslock/data/ReminderRepository;", "", "reminderDao", "Lcom/focuslock/data/ReminderDao;", "(Lcom/focuslock/data/ReminderDao;)V", "remindersFlow", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/focuslock/model/Reminder;", "getRemindersFlow", "()Lkotlinx/coroutines/flow/Flow;", "addReminder", "", "reminder", "(Lcom/focuslock/model/Reminder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteReminder", "ensureUniqueTitle", "", "title", "", "reminderId", "", "(Ljava/lang/String;Ljava/lang/Long;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateReminder", "app_release"})
public final class ReminderRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.focuslock.data.ReminderDao reminderDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.focuslock.model.Reminder>> remindersFlow = null;
    
    public ReminderRepository(@org.jetbrains.annotations.NotNull()
    com.focuslock.data.ReminderDao reminderDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.focuslock.model.Reminder>> getRemindersFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addReminder(@org.jetbrains.annotations.NotNull()
    com.focuslock.model.Reminder reminder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateReminder(@org.jetbrains.annotations.NotNull()
    com.focuslock.model.Reminder reminder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteReminder(@org.jetbrains.annotations.NotNull()
    com.focuslock.model.Reminder reminder, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object ensureUniqueTitle(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.Nullable()
    java.lang.Long reminderId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
}