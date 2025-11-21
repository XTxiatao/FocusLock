package com.focuslock.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.focuslock.R
import com.focuslock.databinding.ActivityMainBinding
import com.focuslock.service.LockOverlayService
import com.focuslock.util.PermissionHelper

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private var permissionDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startServiceButton.setOnClickListener {
            if (checkAllPermissions()) {
                startLockService()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkAllPermissions()
    }

    override fun onDestroy() {
        permissionDialog?.dismiss()
        super.onDestroy()
    }

    private fun checkAllPermissions(): Boolean {
        val missing = PermissionHelper.getMissingPermissions(this)
        if (missing.isEmpty()) {
            binding.statusText.text = getString(R.string.service_notification_text)
            return true
        }

        binding.statusText.text = getString(R.string.permission_missing_title)
        promptForPermission(missing.first())
        return false
    }

    private fun promptForPermission(permission: PermissionHelper.RequiredPermission) {
        if (permissionDialog?.isShowing == true) {
            return
        }

        val title = getString(R.string.permission_missing_title)
        val message = permission.descriptionText(this)
        val intent = permission.settingsIntent(this)

        permissionDialog = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.open_settings_button) { _, _ ->
                PermissionHelper.markPermissionAcknowledged(this, permission)
                intent?.let { startActivity(it) }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .setOnDismissListener { permissionDialog = null }
            .show()
    }

    private fun startLockService() {
        val serviceIntent = Intent(this, LockOverlayService::class.java)
        ContextCompat.startForegroundService(this, serviceIntent)
        binding.statusText.text = getString(R.string.service_notification_text)
    }
}

private fun PermissionHelper.RequiredPermission.descriptionText(activity: ComponentActivity): String {
    return when (this) {
        PermissionHelper.RequiredPermission.OVERLAY -> activity.getString(R.string.permission_missing_message) +
            " Overlay access is needed for floating window."
        PermissionHelper.RequiredPermission.USAGE_STATS -> activity.getString(R.string.permission_missing_message) +
            " Usage data keeps the lock in sync."
        PermissionHelper.RequiredPermission.ACCESSIBILITY -> activity.getString(R.string.permission_missing_message) +
            " Accessibility access powers the input interception."
        PermissionHelper.RequiredPermission.BATTERY -> activity.getString(R.string.permission_missing_message) +
            " Background optimization must be ignored."
        PermissionHelper.RequiredPermission.AUTO_START -> activity.getString(R.string.permission_missing_message) +
            " Allow auto start to keep the service running after boot."
    }
}
