package com.focuslock.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "app_restriction_plan_slots",
    foreignKeys = [
        ForeignKey(
            entity = AppRestrictionPlanEntity::class,
            parentColumns = ["id"],
            childColumns = ["planId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("planId")]
)
data class AppRestrictionPlanSlotEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val planId: Long,
    val startMinutes: Int,
    val endMinutes: Int,
    val daysBitmask: Int
)
