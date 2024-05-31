package cz.airbank.airbanktest.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

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