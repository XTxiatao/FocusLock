package com.focuslock.ui

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.focuslock.FocusLockApplication
import com.focuslock.R
import com.focuslock.databinding.FragmentAppLockBinding
import com.focuslock.model.WhitelistedApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppLockFragment : Fragment() {

    private var _binding: FragmentAppLockBinding? = null
    private val binding get() = _binding!!
    private val displayAdapter by lazy {
        WhitelistDisplayAdapter(::confirmRemoveApp) { packageName ->
            runCatching { requireContext().packageManager.getApplicationIcon(packageName) }.getOrNull()
        }
    }

    private val repository by lazy {
        (requireActivity().application as FocusLockApplication).whitelistedAppRepository
    }

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
        binding.whitelistRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.whitelistRecyclerView.adapter = displayAdapter

        binding.configureWhitelistButton.setOnClickListener {
            openSystemAppPicker()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                repository.whitelistFlow.collect { displayAdapter.submitList(it) }
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
