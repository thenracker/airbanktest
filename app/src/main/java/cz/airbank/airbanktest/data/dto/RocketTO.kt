package cz.airbank.airbanktest.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketDetailTO(
    @SerialName("rocket_name")
    val name: String,
    val description: String,
    @SerialName("wikipedia")
    val link: String?,
)