package com.focuslock.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001bBW\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0004\u00a2\u0006\u0002\u0010\fJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u001c\u0010\u0011\u001a\u00020\u00062\n\u0010\u0012\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0010H\u0016J\u001c\u0010\u0014\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0010H\u0016J\u0014\u0010\u0018\u001a\u00020\u00062\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u001aR\u001c\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/focuslock/ui/AppRestrictionPlanAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/focuslock/ui/AppRestrictionPlanAdapter$ViewHolder;", "onToggle", "Lkotlin/Function1;", "Lcom/focuslock/model/AppRestrictionPlan;", "", "onLongPress", "onEdit", "iconProvider", "", "Landroid/graphics/drawable/Drawable;", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "items", "", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "submitList", "list", "", "ViewHolder", "app_release"})
public final class AppRestrictionPlanAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.focuslock.ui.AppRestrictionPlanAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.focuslock.model.AppRestrictionPlan, kotlin.Unit> onToggle = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.focuslock.model.AppRestrictionPlan, kotlin.Unit> onLongPress = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.focuslock.model.AppRestrictionPlan, kotlin.Unit> onEdit = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<java.lang.String, android.graphics.drawable.Drawable> iconProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.focuslock.model.AppRestrictionPlan> items = null;
    
    public AppRestrictionPlanAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.focuslock.model.AppRestrictionPlan, kotlin.Unit> onToggle, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.focuslock.model.AppRestrictionPlan, kotlin.Unit> onLongPress, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.focuslock.model.AppRestrictionPlan, kotlin.Unit> onEdit, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, ? extends android.graphics.drawable.Drawable> iconProvider) {
        super();
    }
    
    public final void submitList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.focuslock.model.AppRestrictionPlan> list) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.focuslock.ui.AppRestrictionPlanAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.focuslock.ui.AppRestrictionPlanAdapter.ViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/focuslock/ui/AppRestrictionPlanAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/focuslock/databinding/ItemAppRestrictionPlanBinding;", "(Lcom/focuslock/ui/AppRestrictionPlanAdapter;Lcom/focuslock/databinding/ItemAppRestrictionPlanBinding;)V", "bind", "", "plan", "Lcom/focuslock/model/AppRestrictionPlan;", "app_release"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.focuslock.databinding.ItemAppRestrictionPlanBinding binding = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        com.focuslock.databinding.ItemAppRestrictionPlanBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.focuslock.model.AppRestrictionPlan plan) {
        }
    }
}