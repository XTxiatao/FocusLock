package com.focuslock.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\'J\u0016\u0010\n\u001a\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\'J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\'J\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\f0\u0010H\'J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\'\u00a8\u0006\u0013"}, d2 = {"Lcom/focuslock/data/AppRestrictionPlanDao;", "", "deleteMappings", "", "planId", "", "deletePlan", "", "entity", "Lcom/focuslock/data/AppRestrictionPlanEntity;", "insertMappings", "mappings", "", "Lcom/focuslock/data/AppRestrictionPlanAppCrossRef;", "insertPlan", "observePlans", "Lkotlinx/coroutines/flow/Flow;", "Lcom/focuslock/data/AppRestrictionPlanWithApps;", "updatePlan", "app_release"})
@androidx.room.Dao()
public abstract interface AppRestrictionPlanDao {
    
    @androidx.room.Transaction()
    @androidx.room.Query(value = "SELECT * FROM app_restriction_plans ORDER BY id DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.focuslock.data.AppRestrictionPlanWithApps>> observePlans();
    
    @androidx.room.Insert()
    public abstract long insertPlan(@org.jetbrains.annotations.NotNull()
    com.focuslock.data.AppRestrictionPlanEntity entity);
    
    @androidx.room.Update()
    public abstract void updatePlan(@org.jetbrains.annotations.NotNull()
    com.focuslock.data.AppRestrictionPlanEntity entity);
    
    @androidx.room.Delete()
    public abstract void deletePlan(@org.jetbrains.annotations.NotNull()
    com.focuslock.data.AppRestrictionPlanEntity entity);
    
    @androidx.room.Insert(onConflict = 1)
    public abstract void insertMappings(@org.jetbrains.annotations.NotNull()
    java.util.List<com.focuslock.data.AppRestrictionPlanAppCrossRef> mappings);
    
    @androidx.room.Query(value = "DELETE FROM app_restriction_plan_apps WHERE planId = :planId")
    public abstract int deleteMappings(long planId);
}