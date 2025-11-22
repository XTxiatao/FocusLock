package com.focuslock.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n\u00a8\u0006\u0013"}, d2 = {"Lcom/focuslock/data/LockScheduleRepository;", "", "dao", "Lcom/focuslock/data/LockScheduleDao;", "(Lcom/focuslock/data/LockScheduleDao;)V", "enabledSchedulesFlow", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/focuslock/model/LockSchedule;", "getEnabledSchedulesFlow", "()Lkotlinx/coroutines/flow/Flow;", "schedulesFlow", "getSchedulesFlow", "delete", "", "schedule", "(Lcom/focuslock/model/LockSchedule;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "update", "app_release"})
public final class LockScheduleRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.focuslock.data.LockScheduleDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.focuslock.model.LockSchedule>> schedulesFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.focuslock.model.LockSchedule>> enabledSchedulesFlow = null;
    
    public LockScheduleRepository(@org.jetbrains.annotations.NotNull()
    com.focuslock.data.LockScheduleDao dao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.focuslock.model.LockSchedule>> getSchedulesFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.focuslock.model.LockSchedule>> getEnabledSchedulesFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.focuslock.model.LockSchedule schedule, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.focuslock.model.LockSchedule schedule, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.focuslock.model.LockSchedule schedule, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}