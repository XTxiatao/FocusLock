package com.focuslock.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WhitelistedAppDao {
    @Query("SELECT * FROM whitelisted_apps ORDER BY label ASC")
    fun getAll(): Flow<List<WhitelistedAppEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(app: WhitelistedAppEntity)

    @Query("DELETE FROM whitelisted_apps")
    fun clearAll()

    @Query("DELETE FROM whitelisted_apps WHERE packageName = :packageName")
    fun deleteByPackage(packageName: String)
}
