package com.focuslock.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00132\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0003\u0012\u0013\u0014BA\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\tJ\u001c\u0010\n\u001a\u00020\u00062\n\u0010\u000b\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\f\u001a\u00020\rH\u0016J\u001c\u0010\u000e\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\rH\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/focuslock/ui/ArchivedReminderAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/focuslock/model/Reminder;", "Lcom/focuslock/ui/ArchivedReminderAdapter$ArchivedReminderViewHolder;", "restoreListener", "Lkotlin/Function1;", "", "deleteListener", "editListener", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ArchivedReminderViewHolder", "Companion", "Diff", "app_debug"})
public final class ArchivedReminderAdapter extends androidx.recyclerview.widget.ListAdapter<com.focuslock.model.Reminder, com.focuslock.ui.ArchivedReminderAdapter.ArchivedReminderViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.focuslock.model.Reminder, kotlin.Unit> restoreListener = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.focuslock.model.Reminder, kotlin.Unit> deleteListener = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.focuslock.model.Reminder, kotlin.Unit> editListener = null;
    private static final java.time.format.DateTimeFormatter DATE_TIME_FORMATTER = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.focuslock.ui.ArchivedReminderAdapter.Companion Companion = null;
    
    public ArchivedReminderAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.focuslock.model.Reminder, kotlin.Unit> restoreListener, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.focuslock.model.Reminder, kotlin.Unit> deleteListener, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.focuslock.model.Reminder, kotlin.Unit> editListener) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.focuslock.ui.ArchivedReminderAdapter.ArchivedReminderViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.focuslock.ui.ArchivedReminderAdapter.ArchivedReminderViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/focuslock/ui/ArchivedReminderAdapter$ArchivedReminderViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/focuslock/databinding/ItemArchivedReminderBinding;", "(Lcom/focuslock/ui/ArchivedReminderAdapter;Lcom/focuslock/databinding/ItemArchivedReminderBinding;)V", "bind", "", "reminder", "Lcom/focuslock/model/Reminder;", "app_debug"})
    public final class ArchivedReminderViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.focuslock.databinding.ItemArchivedReminderBinding binding = null;
        
        public ArchivedReminderViewHolder(@org.jetbrains.annotations.NotNull()
        com.focuslock.databinding.ItemArchivedReminderBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.focuslock.model.Reminder reminder) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/focuslock/ui/ArchivedReminderAdapter$Companion;", "", "()V", "DATE_TIME_FORMATTER", "Ljava/time/format/DateTimeFormatter;", "kotlin.jvm.PlatformType", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u00c2\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/focuslock/ui/ArchivedReminderAdapter$Diff;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/focuslock/model/Reminder;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    static final class Diff extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.focuslock.model.Reminder> {
        @org.jetbrains.annotations.NotNull()
        public static final com.focuslock.ui.ArchivedReminderAdapter.Diff INSTANCE = null;
        
        private Diff() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        com.focuslock.model.Reminder oldItem, @org.jetbrains.annotations.NotNull()
        com.focuslock.model.Reminder newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        com.focuslock.model.Reminder oldItem, @org.jetbrains.annotations.NotNull()
        com.focuslock.model.Reminder newItem) {
            return false;
        }
    }
}