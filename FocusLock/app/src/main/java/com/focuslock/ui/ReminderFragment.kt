package com.focuslock.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.focuslock.FocusLockApplication
import com.focuslock.R
import com.focuslock.data.ReminderRepository
import com.focuslock.databinding.DialogArchivedRemindersBinding
import com.focuslock.databinding.FragmentReminderBinding
import com.focuslock.model.Reminder
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlinx.coroutines.launch

class ReminderFragment : Fragment() {

    private var _binding: FragmentReminderBinding? = null
    private val binding get() = _binding!!

    private val reminderRepository: ReminderRepository by lazy {
        (requireActivity().application as FocusLockApplication).reminderRepository
    }

    private val dayAdapter by lazy {
        ReminderDayAdapter(::showReminderEditor, ::completeReminderFromPill)
    }
    private val completedAdapter by lazy {
        CompletedDayAdapter(::showReminderEditor, ::attachSwipeToCompletedRecycler)
    }

    private var latestReminders: List<Reminder> = emptyList()
    private var archivedDialog: AlertDialog? = null
    private var archivedDialogBinding: DialogArchivedRemindersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReminderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.reminderRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.reminderRecyclerView.adapter = dayAdapter
        binding.archivedCard.setOnClickListener { showArchivedDialog() }
        binding.addReminderFab.setOnClickListener { showReminderEditor() }
        binding.archivedSubtitle.text =
            resources.getQuantityString(R.plurals.reminder_archived_count, 0, 0)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                reminderRepository.remindersFlow.collect { reminders ->
                    latestReminders = reminders
                    val (timedReminders, floatingReminders) = reminders
                        .filter { !it.isCompleted }
                        .partition { it.anchorDateTimeMillis != null }
                    val activeGroups = ReminderGrouping.buildDayGroups(timedReminders)
                    val floatingGroups = ReminderGrouping.buildFloatingGroup(floatingReminders)
                    dayAdapter.submitList(activeGroups + floatingGroups)
                    binding.reminderRecyclerView.isVisible = (activeGroups + floatingGroups).isNotEmpty()
                    binding.emptyRemindersView.isVisible = (activeGroups + floatingGroups).isEmpty()

                    val completedList = reminders.filter { it.isCompleted }
                    val completedFloating = completedList.filter { it.anchorDateTimeMillis == null }
                    val completedWithDates = completedList.filter { it.anchorDateTimeMillis != null }
                    val completedGroups = ReminderGrouping.buildCompletedGroups(completedWithDates) +
                        ReminderGrouping.buildCompletedFloatingGroup(completedFloating)
                    completedAdapter.submitList(completedGroups)
                    val subtitle = resources.getQuantityString(
                        R.plurals.reminder_archived_count,
                        completedList.size,
                        completedList.size
                    )
                    binding.archivedSubtitle.text = subtitle
                    archivedDialogBinding?.let { dialogBinding ->
                        dialogBinding.archivedEmptyText.isVisible = completedGroups.isEmpty()
                    }
                }
            }
        }
    }

    private fun completeReminderFromPill(reminder: Reminder) {
        if (reminder.isCompleted) return
        viewLifecycleOwner.lifecycleScope.launch {
            reminderRepository.completeReminder(reminder)
        }
    }

    private fun showArchivedDialog() {
        val dialogBinding = DialogArchivedRemindersBinding.inflate(layoutInflater)
        dialogBinding.archivedRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        dialogBinding.archivedRecyclerView.adapter = completedAdapter
        dialogBinding.archivedEmptyText.isVisible = completedAdapter.currentList.isEmpty()
        archivedDialogBinding = dialogBinding
        archivedDialog = AlertDialog.Builder(requireContext())
            .setTitle(R.string.reminder_archived_card_title)
            .setView(dialogBinding.root)
            .setPositiveButton(android.R.string.ok, null)
            .create().also { dialog ->
                dialog.setOnDismissListener {
                    archivedDialogBinding = null
                }
                dialog.show()
            }
    }

    private fun showReminderEditor(reminder: Reminder? = null) {
        showReminderEditorDialog(
            context = requireContext(),
            reminder = reminder,
            repository = reminderRepository,
            scope = viewLifecycleOwner.lifecycleScope
        )
    }

    private fun attachSwipeToCompletedRecycler(
        recyclerView: androidx.recyclerview.widget.RecyclerView,
        adapter: CompletedEntryAdapter
    ) {
        val existing = recyclerView.getTag(R.id.tag_completed_swipe_helper) as? androidx.recyclerview.widget.ItemTouchHelper
        existing?.attachToRecyclerView(null)
        val callback = object : androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback(0,
            androidx.recyclerview.widget.ItemTouchHelper.LEFT or androidx.recyclerview.widget.ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: androidx.recyclerview.widget.RecyclerView,
                viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder,
                target: androidx.recyclerview.widget.RecyclerView.ViewHolder
            ): Boolean = false

            override fun clearView(
                recyclerView: androidx.recyclerview.widget.RecyclerView,
                viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)
                viewHolder.itemView.translationX = 0f
                viewHolder.itemView.alpha = 1f
            }

            override fun onSwiped(viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val reminder = adapter.reminderAt(position)
                if (reminder == null) {
                    adapter.notifyItemChanged(position)
                    return
                }
                when (direction) {
                    androidx.recyclerview.widget.ItemTouchHelper.LEFT ->
                        promptRestoreCompleted(reminder, adapter, position)
                    androidx.recyclerview.widget.ItemTouchHelper.RIGHT ->
                        promptDeleteCompleted(reminder, adapter, position)
                    else -> adapter.notifyItemChanged(position)
                }
            }
        }
        val helper = androidx.recyclerview.widget.ItemTouchHelper(callback)
        helper.attachToRecyclerView(recyclerView)
        recyclerView.setTag(R.id.tag_completed_swipe_helper, helper)
    }

    private fun promptRestoreCompleted(reminder: Reminder, adapter: CompletedEntryAdapter, position: Int) {
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.reminder_restore_completed_confirm)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                adapter.notifyItemChanged(position)
                restoreCompletedReminder(reminder)
            }
            .setNegativeButton(android.R.string.cancel) { _, _ ->
                adapter.notifyItemChanged(position)
            }
            .setOnCancelListener { adapter.notifyItemChanged(position) }
            .show()
    }

    private fun promptDeleteCompleted(reminder: Reminder, adapter: CompletedEntryAdapter, position: Int) {
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.reminder_delete_confirm)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                adapter.notifyItemChanged(position)
                viewLifecycleOwner.lifecycleScope.launch {
                    reminderRepository.deleteReminder(reminder)
                }
            }
            .setNegativeButton(android.R.string.cancel) { _, _ ->
                adapter.notifyItemChanged(position)
            }
            .setOnCancelListener { adapter.notifyItemChanged(position) }
            .show()
    }

    private fun restoreCompletedReminder(reminder: Reminder) {
        viewLifecycleOwner.lifecycleScope.launch {
            reminderRepository.updateReminder(reminder.copy(isCompleted = false))
        }
    }

    override fun onDestroyView() {
        archivedDialog?.dismiss()
        archivedDialogBinding = null
        archivedDialog = null
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy")
        private val TIME_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        private val DATE_TIME_SUMMARY_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("MMM d HH:mm")
    }
}
