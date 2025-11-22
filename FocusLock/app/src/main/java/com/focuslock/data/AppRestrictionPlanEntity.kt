package com.focuslock.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "app_restriction_plans")
data class AppRestrictionPlanEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val startMinutes: Int,
    val endMinutes: Int,
    val daysBitmask: Int,
    val isEnabled: Boolean
)
