package cz.airbank.airbanktest.data

import cz.airbank.airbanktest.data.dto.LaunchTO
import retrofit2.http.GET

interface SpaceXApi {

    @GET("launches")
    suspend fun fetchLaunches(): List<LaunchTO>
}