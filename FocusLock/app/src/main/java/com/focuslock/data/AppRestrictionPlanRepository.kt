package com.focuslock.data

import com.focuslock.model.AppRestrictionPlan
import com.focuslock.model.AppRestrictionPlanSlot
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
                isEnabled = entry.plan.isEnabled,
                slots = entry.slots
                    .map { AppRestrictionPlanSlot(it.startMinutes, it.endMinutes, it.daysBitmask) }
                    .sortedBy { it.startMinutes },
                apps = entry.apps.map { WhitelistedApp(it.packageName, it.label) }
            )
        }
    }

    suspend fun insertPlan(
        slots: List<AppRestrictionPlanSlot>,
        packageNames: List<String>
    ) {
        withContext(Dispatchers.IO) {
            val planId = dao.insertPlan(
                AppRestrictionPlanEntity(
                    isEnabled = false
                )
            )
            persistSlots(planId, slots)
            persistMappings(planId, packageNames)
        }
    }

    suspend fun updatePlan(plan: AppRestrictionPlan) {
        withContext(Dispatchers.IO) {
            dao.updatePlan(
                AppRestrictionPlanEntity(
                    id = plan.id,
                    isEnabled = plan.isEnabled
                )
            )
        }
    }

    suspend fun deletePlan(plan: AppRestrictionPlan) {
        withContext(Dispatchers.IO) {
            dao.deleteMappings(plan.id)
            dao.deleteSlots(plan.id)
            dao.deletePlan(
                AppRestrictionPlanEntity(
                    id = plan.id,
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

    suspend fun updatePlanDetails(
        plan: AppRestrictionPlan,
        slots: List<AppRestrictionPlanSlot>,
        packageNames: List<String>
    ) {
        withContext(Dispatchers.IO) {
            dao.updatePlan(
                AppRestrictionPlanEntity(
                    id = plan.id,
                    isEnabled = plan.isEnabled
                )
            )
            dao.deleteSlots(plan.id)
            persistSlots(plan.id, slots)
            dao.deleteMappings(plan.id)
            persistMappings(plan.id, packageNames)
        }
    }

    private suspend fun persistSlots(planId: Long, slots: List<AppRestrictionPlanSlot>) {
        if (slots.isEmpty()) return
        dao.insertSlots(slots.map { slot ->
            AppRestrictionPlanSlotEntity(
                planId = planId,
                startMinutes = slot.startMinutes,
                endMinutes = slot.endMinutes,
                daysBitmask = slot.daysBitmask
            )
        })
    }

    private suspend fun persistMappings(planId: Long, packageNames: List<String>) {
        dao.insertMappings(packageNames.map { AppRestrictionPlanAppCrossRef(planId, it) })
    }
}
