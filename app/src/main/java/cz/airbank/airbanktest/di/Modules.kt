package cz.airbank.airbanktest.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import cz.airbank.airbanktest.data.SpaceXApi
import cz.airbank.airbanktest.data.datastore.Storage
import cz.airbank.airbanktest.data.db.AppDatabase
import cz.airbank.airbanktest.data.repositories.SpaceXRepo
import cz.airbank.airbanktest.ui.coroutines.CoroutinesViewModel
import cz.airbank.airbanktest.ui.people.PeopleViewModel
import cz.airbank.airbanktest.ui.spacex.SpaceXViewModel
import cz.airbank.airbanktest.ui.spacex.detail.RocketDetailViewModel
import cz.airbank.airbanktest.ui.states.StatesViewModel
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit
import kotlin.math.sin


val dataModule = module {
    repositories()
    api()
    db()
    dataStore()
}

val uiModule = module {
    viewModel { PeopleViewModel() }
    viewModel { CoroutinesViewModel() }
    viewModel { StatesViewModel() }

    // viewModel { SpaceXViewModel(get()) }
    viewModelOf(::SpaceXViewModel)

    viewModel { (rocketId: String) -> RocketDetailViewModel(rocketId, get()) }
}

private fun Module.api() {
    single { createRetrofit() }
    single { get<Retrofit>().create(SpaceXApi::class.java) }
}

private fun Module.db() {
    // DB
    single {
        Room.databaseBuilder(
            context = androidApplication(),
            klass = AppDatabase::class.java,
            name = AppDatabase.Name,
        ).build()
    }

    // DAO
    single { get<AppDatabase>().launchDao() }
    single { get<AppDatabase>().rocketDao() }
}

private fun Module.dataStore() {
    single {
        Storage(androidApplication().dataStore)
    }
}

private val Context.dataStore by preferencesDataStore(Storage.DataStoreName)


private fun Module.repositories() {
    //factory { SpaceXRepo(get(), get(), get(), get(), get()) }
    factoryOf(::SpaceXRepo)
}

private val json = Json {
    ignoreUnknownKeys = true
}

private fun createRetrofit() =
    Retrofit.Builder()
        .client(
            OkHttpClient.Builder().build()
        )
        .baseUrl(
            "https://api.spacexdata.com/v3/"
        )
        .addConverterFactory(
            json.asConverterFactory(
                MediaType.get("application/json")
            )
        )
        .build()