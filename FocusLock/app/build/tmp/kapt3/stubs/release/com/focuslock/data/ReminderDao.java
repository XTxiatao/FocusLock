package com.focuslock.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\'J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\'J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\'J\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00100\u000fH\'J\u0010\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\'\u00a8\u0006\u0012"}, d2 = {"Lcom/focuslock/data/ReminderDao;", "", "countByTitle", "", "title", "", "countByTitleExcluding", "excludeId", "", "delete", "", "reminder", "Lcom/focuslock/data/ReminderEntity;", "insert", "observeAll", "Lkotlinx/coroutines/flow/Flow;", "", "update", "app_release"})
@androidx.room.Dao()
public abstract interface ReminderDao {
    
    @androidx.room.Query(value = "SELECT * FROM reminders")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.focuslock.data.ReminderEntity>> observeAll();
    
    @androidx.room.Insert(onConflict = 3)
    public abstract long insert(@org.jetbrains.annotations.NotNull()
    com.focuslock.data.ReminderEntity reminder);
    
    @androidx.room.Update()
    public abstract void update(@org.jetbrains.annotations.NotNull()
    com.focuslock.data.ReminderEntity reminder);
    
    @androidx.room.Delete()
    public abstract void delete(@org.jetbrains.annotations.NotNull()
    com.focuslock.data.ReminderEntity reminder);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM reminders WHERE LOWER(title) = LOWER(:title)")
    public abstract int countByTitle(@org.jetbrains.annotations.NotNull()
    java.lang.String title);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM reminders WHERE LOWER(title) = LOWER(:title) AND id != :excludeId")
    public abstract int countByTitleExcluding(@org.jetbrains.annotations.NotNull()
    java.lang.String title, long excludeId);
}