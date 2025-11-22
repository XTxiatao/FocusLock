package com.focuslock.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0014\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u0007H\'J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u0007H\'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\r"}, d2 = {"Lcom/focuslock/data/LockScheduleDao;", "", "delete", "", "schedule", "Lcom/focuslock/data/LockScheduleEntity;", "getAll", "Lkotlinx/coroutines/flow/Flow;", "", "getEnabled", "insert", "", "update", "app_release"})
@androidx.room.Dao()
public abstract interface LockScheduleDao {
    
    @androidx.room.Insert()
    public abstract long insert(@org.jetbrains.annotations.NotNull()
    com.focuslock.data.LockScheduleEntity schedule);
    
    @androidx.room.Update()
    public abstract void update(@org.jetbrains.annotations.NotNull()
    com.focuslock.data.LockScheduleEntity schedule);
    
    @androidx.room.Delete()
    public abstract void delete(@org.jetbrains.annotations.NotNull()
    com.focuslock.data.LockScheduleEntity schedule);
    
    @androidx.room.Query(value = "SELECT * FROM lock_schedule ORDER BY id DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.focuslock.data.LockScheduleEntity>> getAll();
    
    @androidx.room.Query(value = "SELECT * FROM lock_schedule WHERE is_enabled = 1 ORDER BY id DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.focuslock.data.LockScheduleEntity>> getEnabled();
}