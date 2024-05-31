package cz.airbank.airbanktest.data.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.airbank.airbanktest.data.db.entities.RocketEntity

@Dao
interface RocketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(rocket: RocketEntity)

    @Delete
    suspend fun delete(rocket: RocketEntity)

    @Query("Select * From RocketEntity")
    suspend fun selectAll(): List<RocketEntity>

    @Query("Select name From RocketEntity Order by name")
    suspend fun selectRocketNames(): List<String>
}