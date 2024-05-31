package cz.airbank.airbanktest.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RocketEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val link: String?,
)
