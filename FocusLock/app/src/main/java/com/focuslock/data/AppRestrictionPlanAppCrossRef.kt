package com.focuslock.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "app_restriction_plan_apps",
    primaryKeys = ["planId", "packageName"],
    indices = [Index("planId"), Index(value = ["packageName"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = AppRestrictionPlanEntity::class,
            parentColumns = ["id"],
            childColumns = ["planId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = WhitelistedAppEntity::class,
            parentColumns = ["packageName"],
            childColumns = ["packageName"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AppRestrictionPlanAppCrossRef(
    val planId: Long,
    val packageName: String
)
