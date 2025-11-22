package com.focuslock.data

import com.focuslock.model.AppRestrictionPlan
import com.focuslock.model.WhitelistedApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AppRestrictionPlanRepository(
    private val dao: AppRestrictionPlanDao
) {

    val plansFlow: Flow<List<AppRestrictionPlan>> = dao.observePlans().map { list ->
        list.map { entry ->
            AppRestrictionPlan(
                id = entry.plan.id,
                startMinutes = entry.plan.startMinutes,
                endMinutes = entry.plan.endMinutes,
                daysBitmask = entry.plan.daysBitmask,
                isEnabled = entry.plan.isEnabled,
                apps = entry.apps.map { WhitelistedApp(it.packageName, it.label) }
            )
        }
    }

    suspend fun insertPlan(
        startMinutes: Int,
        endMinutes: Int,
        daysBitmask: Int,
        packageNames: List<String>
    ) {
        withContext(Dispatchers.IO) {
            val planId = dao.insertPlan(
                AppRestrictionPlanEntity(
                    startMinutes = startMinutes,
                    endMinutes = endMinutes,
                    daysBitmask = daysBitmask,
                    isEnabled = false
                )
            )
            persistMappings(planId, packageNames)
        }
    }

    suspend fun updatePlan(plan: AppRestrictionPlan) {
        withContext(Dispatchers.IO) {
            dao.updatePlan(
                AppRestrictionPlanEntity(
                    id = plan.id,
                    startMinutes = plan.startMinutes,
                    endMinutes = plan.endMinutes,
                    daysBitmask = plan.daysBitmask,
                    isEnabled = plan.isEnabled
                )
            )
        }
    }

    suspend fun deletePlan(plan: AppRestrictionPlan) {
        withContext(Dispatchers.IO) {
            dao.deleteMappings(plan.id)
            dao.deletePlan(
                AppRestrictionPlanEntity(
                    id = plan.id,
                    startMinutes = plan.startMinutes,
                    endMinutes = plan.endMinutes,
                    daysBitmask = plan.daysBitmask,
                    isEnabled = plan.isEnabled
                )
            )
        }
    }

    suspend fun replacePlanApps(planId: Long, packageNames: List<String>) {
        withContext(Dispatchers.IO) {
            dao.deleteMappings(planId)
            persistMappings(planId, packageNames)
        }
    }

    private suspend fun persistMappings(planId: Long, packageNames: List<String>) {
        dao.insertMappings(packageNames.map { AppRestrictionPlanAppCrossRef(planId, it) })
    }
}
