package cz.airbank.airbanktest.data.repositories

import cz.airbank.airbanktest.data.SpaceXApi
import cz.airbank.airbanktest.data.datastore.Storage
import cz.airbank.airbanktest.data.db.daos.LaunchDao
import cz.airbank.airbanktest.data.db.entities.toEntity
import cz.airbank.airbanktest.data.db.entities.toTO
import cz.airbank.airbanktest.data.dto.LaunchTO

class SpaceXRepo(
    private val api: SpaceXApi,
    private val launchDao: LaunchDao,
    private val storage: Storage,
) {

    suspend fun fetchOrSelectLaunches(): List<LaunchTO> {

        val currentMillis = System.currentTimeMillis()
        val lastMillis = storage.getLaunchesTimestamp()

        val shouldRefresh = (currentMillis - RefreshValidity) > lastMillis

        return if (shouldRefresh) {
            fetchLaunches()
        } else {
            selectLaunches()
        }
    }

    private suspend fun selectLaunches() = launchDao.selectAll().map { it.toTO() }

    private suspend fun fetchLaunches() = api.fetchLaunches().also { launchTOs ->
        val launchEntities = launchTOs.map { launchTO ->
            launchTO.toEntity()
        }
        launchDao.deleteAll()
        launchEntities.forEach { entity ->
            launchDao.insertOrUpdate(entity)
        }
        storage.setLaunchesTimestamp(System.currentTimeMillis())
    }

    suspend fun fetchRocketDetail(id: String) = api.fetchRocketDetail(rocketId = id)

    companion object {
        private const val RefreshValidity = 5 * 60 * 1_000L // 5 minutes
    }
}