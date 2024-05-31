package cz.airbank.airbanktest.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import cz.airbank.airbanktest.data.dto.LaunchTO
import cz.airbank.airbanktest.data.dto.RocketTO

@Entity
data class LaunchEntity(

    // Auto ID
    //    @PrimaryKey(autoGenerate = true)
    //    val id: Long = 0L,

    @PrimaryKey
    val flightNumber: Int,
    val missionName: String,
    val rocketId: String,
    val rocketName: String,
)

fun LaunchTO.toEntity() = LaunchEntity(
    flightNumber = this.flightNumber,
    missionName = this.missionName,
    rocketId = this.rocket.id,
    rocketName = this.rocket.name,
)

fun LaunchEntity.toTO() = LaunchTO(
    flightNumber = this.flightNumber,
    missionName = this.missionName,
    rocket = RocketTO(
        id = this.rocketId,
        name = this.rocketName,
    ),
)

//fun convert(launch: LaunchTO) = LaunchTO(
//    flightNumber = launch.flightNumber,
//)