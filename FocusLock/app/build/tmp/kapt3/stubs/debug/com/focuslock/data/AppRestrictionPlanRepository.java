package com.focuslock.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eJ4\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0007H\u0086@\u00a2\u0006\u0002\u0010\u0016J$\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0007H\u0082@\u00a2\u0006\u0002\u0010\u001aJ$\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0007H\u0086@\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001c\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u001d"}, d2 = {"Lcom/focuslock/data/AppRestrictionPlanRepository;", "", "dao", "Lcom/focuslock/data/AppRestrictionPlanDao;", "(Lcom/focuslock/data/AppRestrictionPlanDao;)V", "plansFlow", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/focuslock/model/AppRestrictionPlan;", "getPlansFlow", "()Lkotlinx/coroutines/flow/Flow;", "deletePlan", "", "plan", "(Lcom/focuslock/model/AppRestrictionPlan;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertPlan", "startMinutes", "", "endMinutes", "daysBitmask", "packageNames", "", "(IIILjava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "persistMappings", "planId", "", "(JLjava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "replacePlanApps", "updatePlan", "app_debug"})
public final class AppRestrictionPlanRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.focuslock.data.AppRestrictionPlanDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.focuslock.model.AppRestrictionPlan>> plansFlow = null;
    
    public AppRestrictionPlanRepository(@org.jetbrains.annotations.NotNull()
    com.focuslock.data.AppRestrictionPlanDao dao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.focuslock.model.AppRestrictionPlan>> getPlansFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertPlan(int startMinutes, int endMinutes, int daysBitmask, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> packageNames, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updatePlan(@org.jetbrains.annotations.NotNull()
    com.focuslock.model.AppRestrictionPlan plan, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deletePlan(@org.jetbrains.annotations.NotNull()
    com.focuslock.model.AppRestrictionPlan plan, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object replacePlanApps(long planId, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> packageNames, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object persistMappings(long planId, java.util.List<java.lang.String> packageNames, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}