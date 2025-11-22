package com.focuslock.util

import android.accessibilityservice.AccessibilityServiceInfo
import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.os.Process
import android.provider.Settings
import android.view.accessibility.AccessibilityManager

object PermissionHelper {

    enum class RequiredPermission {
        OVERLAY,
        USAGE_STATS,
        ACCESSIBILITY,
        BATTERY;

        fun settingsIntent(context: Context): Intent? {
            return when (this) {
                OVERLAY -> Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:${context.packageName}"))
                USAGE_STATS -> Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
                ACCESSIBILITY -> Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                BATTERY -> Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS)
            }
        }
    }

    fun getMissingPermissions(context: Context): List<RequiredPermission> {
        val missing = mutableListOf<RequiredPermission>()

        if (!Settings.canDrawOverlays(context)) {
            missing.add(RequiredPermission.OVERLAY)
        }

        if (!hasUsageStatsPermission(context)) {
            missing.add(RequiredPermission.USAGE_STATS)
        }

        if (!hasAccessibilityPermission(context)) {
            missing.add(RequiredPermission.ACCESSIBILITY)
        }

        if (!hasIgnoreBatteryOptimizations(context)) {
            missing.add(RequiredPermission.BATTERY)
        }

        return missing
    }

    fun hasUsageStatsPermission(context: Context): Boolean {
        val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as? AppOpsManager ?: return false
        val mode = appOps.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            Process.myUid(),
            context.packageName
        )
        return mode == AppOpsManager.MODE_ALLOWED
    }

    private fun hasAccessibilityPermission(context: Context): Boolean {
        val accessibilityManager = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as? AccessibilityManager
            ?: return false
        val enabled = accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK)
        return enabled.any { it.resolveInfo.serviceInfo.packageName == context.packageName }
    }

    private fun hasIgnoreBatteryOptimizations(context: Context): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val powerManager = context.getSystemService(Context.POWER_SERVICE) as? PowerManager ?: return false
            return powerManager.isIgnoringBatteryOptimizations(context.packageName)
        }
        return true
    }

}
