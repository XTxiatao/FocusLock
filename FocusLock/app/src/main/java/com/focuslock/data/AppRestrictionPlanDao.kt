package com.focuslock.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AppRestrictionPlanDao {

    @Transaction
    @Query("SELECT * FROM app_restriction_plans ORDER BY id DESC")
    fun observePlans(): Flow<List<AppRestrictionPlanWithApps>>

    @Insert
    fun insertPlan(entity: AppRestrictionPlanEntity): Long

    @Update
    fun updatePlan(entity: AppRestrictionPlanEntity)

    @Delete
    fun deletePlan(entity: AppRestrictionPlanEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMappings(mappings: List<AppRestrictionPlanAppCrossRef>)

    @Query("DELETE FROM app_restriction_plan_apps WHERE planId = :planId")
    fun deleteMappings(planId: Long): Int
}
