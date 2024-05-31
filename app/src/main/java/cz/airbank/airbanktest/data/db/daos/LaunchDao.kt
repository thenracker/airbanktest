package cz.airbank.airbanktest.data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.airbank.airbanktest.data.db.entities.LaunchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LaunchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(launch: LaunchEntity)

    @Query("Select * From LaunchEntity")
    suspend fun selectAll(): List<LaunchEntity>

    @Query("Select * From LaunchEntity")
    fun selectAllFlow(): Flow<List<LaunchEntity>>

}