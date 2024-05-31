package cz.airbank.airbanktest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.airbank.airbanktest.data.db.daos.LaunchDao
import cz.airbank.airbanktest.data.db.daos.RocketDao
import cz.airbank.airbanktest.data.db.entities.LaunchEntity
import cz.airbank.airbanktest.data.db.entities.RocketEntity

@Database(
    version = AppDatabase.Version,
    entities = [
        LaunchEntity::class,
        RocketEntity::class,
    ],
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun launchDao(): LaunchDao
    abstract fun rocketDao(): RocketDao

    companion object {
        const val Version = 1
        const val Name = "AirBankTest-db"
    }
}