package cz.airbank.airbanktest.data.repositories

import cz.airbank.airbanktest.data.SpaceXApi
import cz.airbank.airbanktest.data.db.daos.LaunchDao
import cz.airbank.airbanktest.data.db.entities.toEntity
import cz.airbank.airbanktest.data.db.entities.toTO
import cz.airbank.airbanktest.data.dto.LaunchTO
import kotlin.random.Random

class SpaceXRepo(
    private val api: SpaceXApi,
    private val launchDao: LaunchDao,
) {

    suspend fun fetchOrSelectLaunches(): List<LaunchTO> {
        // TODO decide by timestamp (older than 1 minute, fetch from API)
        return if (Random.nextBoolean()) {
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
        // TODO save timestamp
    }

    suspend fun fetchRocketDetail(id: String) = api.fetchRocketDetail(rocketId = id)

}