package com.focuslock.data

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class AppRestrictionPlanWithApps(
    @Embedded val plan: AppRestrictionPlanEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "packageName",
        associateBy = Junction(
            value = AppRestrictionPlanAppCrossRef::class,
            parentColumn = "planId",
            entityColumn = "packageName"
        )
    )
    val apps: List<WhitelistedAppEntity>
)
