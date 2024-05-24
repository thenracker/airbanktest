package cz.airbank.airbanktest.data

import cz.airbank.airbanktest.data.dto.LaunchTO
import cz.airbank.airbanktest.data.dto.RocketDetailTO
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXApi {

    @GET("launches")
    suspend fun fetchLaunches(): List<LaunchTO>

    @GET("rockets/{id}")
    suspend fun fetchRocketDetail(
        @Path("id") rocketId: String,
    ): RocketDetailTO?

}