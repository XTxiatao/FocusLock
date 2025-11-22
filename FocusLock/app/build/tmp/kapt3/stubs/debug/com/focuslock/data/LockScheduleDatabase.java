package com.focuslock.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&\u00a8\u0006\u000b"}, d2 = {"Lcom/focuslock/data/LockScheduleDatabase;", "Landroidx/room/RoomDatabase;", "()V", "appRestrictionPlanDao", "Lcom/focuslock/data/AppRestrictionPlanDao;", "lockScheduleDao", "Lcom/focuslock/data/LockScheduleDao;", "reminderDao", "Lcom/focuslock/data/ReminderDao;", "whitelistedAppDao", "Lcom/focuslock/data/WhitelistedAppDao;", "app_debug"})
@androidx.room.Database(entities = {com.focuslock.data.LockScheduleEntity.class, com.focuslock.data.WhitelistedAppEntity.class, com.focuslock.data.AppRestrictionPlanEntity.class, com.focuslock.data.AppRestrictionPlanAppCrossRef.class, com.focuslock.data.ReminderEntity.class}, version = 6, exportSchema = false)
public abstract class LockScheduleDatabase extends androidx.room.RoomDatabase {
    
    public LockScheduleDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.focuslock.data.LockScheduleDao lockScheduleDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.focuslock.data.WhitelistedAppDao whitelistedAppDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.focuslock.data.AppRestrictionPlanDao appRestrictionPlanDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.focuslock.data.ReminderDao reminderDao();
}