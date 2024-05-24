package cz.airbank.airbanktest.data.repositories

import cz.airbank.airbanktest.data.SpaceXApi

class SpaceXRepo(
    private val api: SpaceXApi,
) {

    suspend fun fetchLaunches() = api.fetchLaunches()

    suspend fun fetchRocketDetail(id: String) = api.fetchRocketDetail(rocketId = id)

}