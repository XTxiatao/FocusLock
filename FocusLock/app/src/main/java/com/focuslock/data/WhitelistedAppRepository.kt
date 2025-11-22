package com.focuslock.data

import com.focuslock.model.WhitelistedApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class WhitelistedAppRepository(
    private val dao: WhitelistedAppDao
) {

    val whitelistFlow: Flow<List<WhitelistedApp>> = dao.getAll().map { apps ->
        apps.map { WhitelistedApp(it.packageName, it.label) }
    }

    suspend fun addApp(app: WhitelistedApp) {
        withContext(Dispatchers.IO) {
            dao.insert(WhitelistedAppEntity(app.packageName, app.label))
        }
    }

    suspend fun removeApp(packageName: String) {
        withContext(Dispatchers.IO) {
            dao.deleteByPackage(packageName)
        }
    }
}
