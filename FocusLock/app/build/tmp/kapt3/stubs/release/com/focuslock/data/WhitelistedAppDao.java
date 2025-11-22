package com.focuslock.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\'J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\nH\'\u00a8\u0006\r"}, d2 = {"Lcom/focuslock/data/WhitelistedAppDao;", "", "clearAll", "", "deleteByPackage", "packageName", "", "getAll", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/focuslock/data/WhitelistedAppEntity;", "insert", "app", "app_release"})
@androidx.room.Dao()
public abstract interface WhitelistedAppDao {
    
    @androidx.room.Query(value = "SELECT * FROM whitelisted_apps ORDER BY label ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.focuslock.data.WhitelistedAppEntity>> getAll();
    
    @androidx.room.Insert(onConflict = 1)
    public abstract void insert(@org.jetbrains.annotations.NotNull()
    com.focuslock.data.WhitelistedAppEntity app);
    
    @androidx.room.Query(value = "DELETE FROM whitelisted_apps")
    public abstract void clearAll();
    
    @androidx.room.Query(value = "DELETE FROM whitelisted_apps WHERE packageName = :packageName")
    public abstract void deleteByPackage(@org.jetbrains.annotations.NotNull()
    java.lang.String packageName);
}