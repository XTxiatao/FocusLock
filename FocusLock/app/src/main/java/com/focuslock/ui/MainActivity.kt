package com.focuslock.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.focuslock.R
import com.focuslock.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_device_lock -> switchFragment(DeviceLockFragment())
                R.id.nav_app_lock -> switchFragment(AppLockFragment())
                R.id.nav_reminder -> switchFragment(ReminderFragment())
            }
            true
        }

        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.nav_device_lock
        }

        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun handleIntent(intent: Intent?) {
        when (intent?.getStringExtra(EXTRA_START_DESTINATION)) {
            DEST_REMINDER -> binding.bottomNavigation.selectedItemId = R.id.nav_reminder
        }
    }

    companion object {
        const val EXTRA_START_DESTINATION = "extra_start_destination"
        const val DEST_REMINDER = "reminder"
    }
}
