package cz.airbank.airbanktest.di

import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import cz.airbank.airbanktest.data.SpaceXApi
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
import org.koin.dsl.module
import retrofit2.Retrofit


val dataModule = module {
    repositories()
    api()
    db()
}

val uiModule = module {
    viewModel { PeopleViewModel() }
    viewModel { CoroutinesViewModel() }
    viewModel { StatesViewModel() }

    // viewModel { SpaceXViewModel(get()) }
    viewModelOf(::SpaceXViewModel)

    viewModel { (rocketId: String) -> RocketDetailViewModel(rocketId, get()) }

    // TODO
    //  DBViewModel
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

private fun Module.repositories() {
    factory { SpaceXRepo(get()) }
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