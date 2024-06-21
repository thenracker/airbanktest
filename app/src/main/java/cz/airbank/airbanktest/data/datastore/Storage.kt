package cz.airbank.airbanktest.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class Storage(
    private val dataStore: DataStore<Preferences>,
) {

    val launchesTimestampFlow =
        dataStore.data.map { it[LaunchesTimestampKey] ?: 0L }

    suspend fun getLaunchesTimestamp() =
        dataStore.data.map { it[LaunchesTimestampKey] }.first() ?: 0L

    suspend fun setLaunchesTimestamp(millis: Long) = dataStore.edit {
        it[LaunchesTimestampKey] = millis
    }

    companion object {
        const val DataStoreName = "AirTestDataStore"
        private val LaunchesTimestampKey = longPreferencesKey("LaunchesTimestampKey")
    }
}