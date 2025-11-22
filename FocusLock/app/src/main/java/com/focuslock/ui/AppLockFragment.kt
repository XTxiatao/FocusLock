package com.focuslock.ui

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.LinearLayout
import com.focuslock.FocusLockApplication
import com.focuslock.R
import com.focuslock.databinding.DialogAddAppPlanBinding
import com.focuslock.databinding.DialogAppSelectorBinding
import com.focuslock.databinding.FragmentAppLockBinding
import com.focuslock.databinding.ItemAppSelectBinding
import com.focuslock.databinding.ItemWhitelistAppBinding
import com.focuslock.model.AppRestrictionPlan
import com.focuslock.model.WhitelistedApp
import com.focuslock.service.LockOverlayService
import kotlinx.coroutines.launch
import java.time.DayOfWeek

class AppLockFragment : Fragment() {

    private var _binding: FragmentAppLockBinding? = null
    private val binding get() = _binding!!
    private val displayAdapter by lazy {
        WhitelistDisplayAdapter(::confirmRemoveApp) { packageName ->
            loadAppIcon(packageName)
        }
    }
    private val appPlanAdapter by lazy {
        AppRestrictionPlanAdapter(::toggleRestrictionPlan, ::confirmDeletePlan, ::requestEditAppPlan) { packageName ->
            loadAppIcon(packageName)
        }
    }

    private val repository by lazy {
        (requireActivity().application as FocusLockApplication).whitelistedAppRepository
    }
    private val restrictionPlanRepository by lazy {
        (requireActivity().application as FocusLockApplication).appRestrictionPlanRepository
    }

    private var latestWhitelist: List<WhitelistedApp> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppLockBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val pickAppLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            handleAppPicked(result)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.whitelistRecyclerView.applyResponsiveGrid()
        binding.whitelistRecyclerView.adapter = displayAdapter

        binding.configureWhitelistButton.setOnClickListener {
            openSystemAppPicker()
        }

        binding.appPlanRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.appPlanRecyclerView.adapter = appPlanAdapter
        binding.addAppPlanButton.setOnClickListener { showAddRestrictionPlanDialog() }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                repository.whitelistFlow.collect {
                    latestWhitelist = it
                    displayAdapter.submitList(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                restrictionPlanRepository.plansFlow.collect { plans ->
                    val sanitized = plans.map { plan ->
                        if (plan.isEnabled && plan.apps.isEmpty()) {
                            viewLifecycleOwner.lifecycleScope.launch {
                                restrictionPlanRepository.updatePlan(plan.copy(isEnabled = false))
                            }
                            plan.copy(isEnabled = false)
                        } else {
                            plan
                        }
                    }
                    appPlanAdapter.submitList(sanitized)
                }
            }
        }
    }

    private fun openSystemAppPicker() {
        val baseIntent = Intent(Intent.ACTION_MAIN, null)
        baseIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        val pickIntent = Intent(Intent.ACTION_PICK_ACTIVITY).apply {
            putExtra(Intent.EXTRA_INTENT, baseIntent)
            putExtra(Intent.EXTRA_TITLE, getString(R.string.select_whitelist_app_title))
        }
        try {
            pickAppLauncher.launch(pickIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(requireContext(), "Cannot open app picker", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleAppPicked(result: ActivityResult) {
        if (result.resultCode != Activity.RESULT_OK) return
        val data = result.data ?: return
        val component = data.component ?: data.resolveActivity(requireContext().packageManager) ?: return
        val packageName = component.packageName
        val label = runCatching {
            val appInfo = requireContext().packageManager.getApplicationInfo(packageName, 0)
            requireContext().packageManager.getApplicationLabel(appInfo).toString()
        }.getOrDefault(packageName)
        viewLifecycleOwner.lifecycleScope.launch {
            repository.addApp(WhitelistedApp(packageName, label))
        }
    }

    private fun confirmRemoveApp(app: WhitelistedApp) {
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setMessage("Remove ${app.label} from whitelist?")
            .setPositiveButton(android.R.string.ok) { _, _ ->
                viewLifecycleOwner.lifecycleScope.launch {
                    repository.removeApp(app.packageName)
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun requestEditAppPlan(plan: AppRestrictionPlan) {
        if (plan.isEnabled) {
            AlertDialog.Builder(requireContext())
                .setMessage(R.string.disable_restriction_to_edit)
                .setPositiveButton(android.R.string.ok, null)
                .show()
        } else {
            showAddRestrictionPlanDialog(plan)
        }
    }

    private fun showAddRestrictionPlanDialog(existing: AppRestrictionPlan? = null) {
        if (latestWhitelist.isEmpty()) {
            Toast.makeText(requireContext(), R.string.restriction_requires_whitelist, Toast.LENGTH_SHORT).show()
            return
        }
        val dialogBinding = DialogAddAppPlanBinding.inflate(layoutInflater)
        var startMinutesLocal = existing?.startMinutes ?: 20 * 60
        var endMinutesLocal = existing?.endMinutes ?: 22 * 60
        dialogBinding.startHourPicker.apply {
            minValue = 0
            maxValue = 23
            value = startMinutesLocal / 60
            setFormatter { String.format("%02d", it) }
        }
        dialogBinding.startMinutePicker.apply {
            minValue = 0
            maxValue = 59
            value = startMinutesLocal % 60
            setFormatter { String.format("%02d", it) }
        }
        dialogBinding.endHourPicker.apply {
            minValue = 0
            maxValue = 23
            value = endMinutesLocal / 60
            setFormatter { String.format("%02d", it) }
        }
        dialogBinding.endMinutePicker.apply {
            minValue = 0
            maxValue = 59
            value = endMinutesLocal % 60
            setFormatter { String.format("%02d", it) }
        }
        val selectedDays = existing?.let { plan ->
            DayOfWeek.values().filter { plan.isDaySelected(it) }.toMutableSet()
        } ?: mutableSetOf<DayOfWeek>().apply { addAll(DayOfWeek.values()) }
        val selectAllByDefault = existing == null
        val dayChipMap = mapOf(
            dialogBinding.dialogChipMonday to DayOfWeek.MONDAY,
            dialogBinding.dialogChipTuesday to DayOfWeek.TUESDAY,
            dialogBinding.dialogChipWednesday to DayOfWeek.WEDNESDAY,
            dialogBinding.dialogChipThursday to DayOfWeek.THURSDAY,
            dialogBinding.dialogChipFriday to DayOfWeek.FRIDAY,
            dialogBinding.dialogChipSaturday to DayOfWeek.SATURDAY,
            dialogBinding.dialogChipSunday to DayOfWeek.SUNDAY
        )
        dayChipMap.forEach { (chip, day) ->
            val checked = if (selectAllByDefault) true else selectedDays.contains(day)
            chip.isChecked = checked
            if (selectAllByDefault && !selectedDays.contains(day)) {
                selectedDays.add(day)
            }
            chip.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) selectedDays.add(day) else selectedDays.remove(day)
            }
        }

        val selectedPackages = existing?.apps?.map { it.packageName }?.toMutableSet() ?: mutableSetOf()
        updateSelectedAppPreview(dialogBinding, selectedPackages)

        dialogBinding.selectAppsButton.setOnClickListener {
            showAppSelectionDialog(selectedPackages, dialogBinding)
        }

        AlertDialog.Builder(requireContext())
            .setTitle(if (existing == null) R.string.restriction_plan_dialog_title else R.string.edit_restriction_plan_title)
            .setView(dialogBinding.root)
            .setPositiveButton(R.string.save_plan_button) { _, _ ->
                startMinutesLocal = dialogBinding.startHourPicker.value * 60 + dialogBinding.startMinutePicker.value
                endMinutesLocal = dialogBinding.endHourPicker.value * 60 + dialogBinding.endMinutePicker.value
                if (selectedDays.isEmpty()) {
                    Toast.makeText(requireContext(), R.string.select_days_hint, Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                if (selectedPackages.isEmpty()) {
                    Toast.makeText(requireContext(), R.string.select_apps_hint, Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                val mask = selectedDays.fold(0) { acc, day -> acc or (1 shl day.ordinal) }
                viewLifecycleOwner.lifecycleScope.launch {
                    if (existing == null) {
                        restrictionPlanRepository.insertPlan(
                            startMinutesLocal,
                            endMinutesLocal,
                            mask,
                            selectedPackages.toList()
                        )
                    } else {
                        restrictionPlanRepository.updatePlanDetails(
                            existing,
                            startMinutesLocal,
                            endMinutesLocal,
                            mask,
                            selectedPackages.toList()
                        )
                    }
                    Toast.makeText(requireContext(), R.string.restriction_plan_saved, Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun showAppSelectionDialog(
        selectedPackages: MutableSet<String>,
        dialogBinding: DialogAddAppPlanBinding
    ) {
        if (latestWhitelist.isEmpty()) return
        val selectorBinding = DialogAppSelectorBinding.inflate(layoutInflater)
        val adapter = AppSelectionAdapter(latestWhitelist, selectedPackages)
        selectorBinding.appRecyclerView.applyResponsiveGrid()
        selectorBinding.appRecyclerView.adapter = adapter

        AlertDialog.Builder(requireContext())
            .setTitle(R.string.select_restricted_apps)
            .setView(selectorBinding.root)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                selectedPackages.clear()
                selectedPackages.addAll(adapter.currentSelection())
                updateSelectedAppPreview(dialogBinding, selectedPackages)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun updateSelectedAppPreview(
        dialogBinding: DialogAddAppPlanBinding,
        selectedPackages: Set<String>
    ) {
        dialogBinding.selectedAppsValue.text =
            getString(R.string.selected_app_count, selectedPackages.size)
        dialogBinding.selectedAppsContainer.removeAllViews()
        val inflater = layoutInflater
        val defaultIcon = android.R.drawable.sym_def_app_icon
        selectedPackages.mapNotNull { pkg ->
            latestWhitelist.firstOrNull { it.packageName == pkg }
        }.forEach { app ->
            val itemBinding =
                ItemWhitelistAppBinding.inflate(inflater, dialogBinding.selectedAppsContainer, false)
            itemBinding.appLabel.text = app.label
            val icon = loadAppIcon(app.packageName)
            itemBinding.appIcon.setImageDrawable(
                icon ?: ContextCompat.getDrawable(requireContext(), defaultIcon)
            )
            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(8, 8, 8, 8)
            }
            dialogBinding.selectedAppsContainer.addView(itemBinding.root, params)
        }
        dialogBinding.selectedAppsScroll.isVisible = selectedPackages.isNotEmpty()
    }

    private fun toggleRestrictionPlan(plan: AppRestrictionPlan) {
        if (!plan.isEnabled && plan.apps.isEmpty()) {
            Toast.makeText(requireContext(), R.string.restriction_requires_apps, Toast.LENGTH_SHORT).show()
            return
        }
        val updated = plan.copy(isEnabled = !plan.isEnabled)
        viewLifecycleOwner.lifecycleScope.launch {
            restrictionPlanRepository.updatePlan(updated)
            if (updated.isEnabled) {
                startLockService()
            }
        }
    }

    private fun confirmDeletePlan(plan: AppRestrictionPlan) {
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.confirm_delete_restriction_plan)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                viewLifecycleOwner.lifecycleScope.launch {
                    restrictionPlanRepository.deletePlan(plan)
                    Toast.makeText(requireContext(), R.string.restriction_plan_deleted, Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun loadAppIcon(packageName: String) =
        runCatching { requireContext().packageManager.getApplicationIcon(packageName) }.getOrNull()

    private fun startLockService() {
        val intent = Intent(requireContext(), LockOverlayService::class.java)
        ContextCompat.startForegroundService(requireContext(), intent)
    }

    private fun RecyclerView.applyResponsiveGrid() {
        layoutManager = GridLayoutManager(context, 4)
    }

    private inner class AppSelectionAdapter(
        private val apps: List<WhitelistedApp>,
        preselected: Set<String>
    ) : RecyclerView.Adapter<AppSelectionAdapter.ViewHolder>() {

        private val selected = preselected.toMutableSet()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = ItemAppSelectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(apps[position])
        }

        override fun getItemCount(): Int = apps.size

        fun currentSelection(): Set<String> = selected.toSet()

        inner class ViewHolder(
            private val binding: ItemAppSelectBinding
        ) : RecyclerView.ViewHolder(binding.root) {

            fun bind(app: WhitelistedApp) {
                binding.appLabel.text = app.label
                val icon = loadAppIcon(app.packageName)
                binding.appIcon.setImageDrawable(
                    icon ?: ContextCompat.getDrawable(
                        binding.root.context,
                        android.R.drawable.sym_def_app_icon
                    )
                )
                binding.appCheckBox.setOnCheckedChangeListener(null)
                binding.appCheckBox.isChecked = selected.contains(app.packageName)
                binding.appCheckBox.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) selected.add(app.packageName) else selected.remove(app.packageName)
                }
                binding.root.setOnClickListener {
                    binding.appCheckBox.isChecked = !binding.appCheckBox.isChecked
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
