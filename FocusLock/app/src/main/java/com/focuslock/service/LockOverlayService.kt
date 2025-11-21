package com.focuslock.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.graphics.PixelFormat
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.core.app.NotificationCompat
import com.focuslock.R
import com.focuslock.databinding.OverlayViewBinding
import android.provider.Settings

class LockOverlayService : Service() {

    private val channelId = "focus_lock_channel"
    private val notificationId = 101
    private val countdownMillis = 90_000L

    private var timer: CountDownTimer? = null
    private var overlayView: View? = null
    private lateinit var binding: OverlayViewBinding
    private lateinit var windowManager: WindowManager

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            stopSelf()
            return
        }
        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        binding = OverlayViewBinding.inflate(LayoutInflater.from(this))
        prepareOverlay()
        binding.countdownText.text = getString(
            R.string.lock_countdown_label,
            (countdownMillis / 1000L).toInt()
        )
    }

    private fun prepareOverlay() {
        binding.root.setOnTouchListener { _, _ -> true }
        binding.forceUnlockButton.setOnClickListener {
            stopSelf()
        }

        val type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            WindowManager.LayoutParams.TYPE_PHONE
        }

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            type,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
            PixelFormat.TRANSLUCENT
        )

        params.gravity = Gravity.TOP or Gravity.START
        overlayView = binding.root
        windowManager.addView(binding.root, params)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startForeground(
                notificationId,
                buildNotification(),
                ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
            )
        } else {
            startForeground(notificationId, buildNotification())
        }
        timer?.cancel()
        timer = object : CountDownTimer(countdownMillis, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                binding.countdownText.text = getString(R.string.lock_countdown_label, seconds)
            }

            override fun onFinish() {
                binding.countdownText.text = getString(R.string.lock_countdown_label, 0)
            }
        }.apply {
            start()
        }

        return START_STICKY
    }

    private fun buildNotification(): Notification {
        ensureChannel()
        return NotificationCompat.Builder(this, channelId)
            .setContentTitle(getString(R.string.service_notification_title))
            .setContentText(getString(R.string.service_notification_text))
            .setSmallIcon(android.R.drawable.ic_lock_lock)
            .setOngoing(true)
            .build()
    }

    private fun ensureChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return
        }

        val manager = getSystemService(NotificationManager::class.java)
        val channel = NotificationChannel(
            channelId,
            getString(R.string.service_notification_title),
            NotificationManager.IMPORTANCE_LOW
        ).apply {
            description = getString(R.string.service_notification_text)
        }
        manager?.createNotificationChannel(channel)
    }

    override fun onDestroy() {
        timer?.cancel()
        overlayView?.let { windowManager.removeViewImmediate(it) }
        overlayView = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
