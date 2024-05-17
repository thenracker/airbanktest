package cz.airbank.airbanktest.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LaunchTO(
    @SerialName("flight_number")
    val flightNumber: Int,
    @SerialName("mission_name")
    val missionName: String,
    val rocket: RocketTO,
)

@Serializable
data class RocketTO(
    @SerialName("rocket_id")
    val id: String,
    @SerialName("rocket_name")
    val name: String,
)