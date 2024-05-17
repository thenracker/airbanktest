package cz.airbank.airbanktest.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import cz.airbank.airbanktest.data.SpaceXApi
import cz.airbank.airbanktest.data.repositories.SpaceXRepo
import cz.airbank.airbanktest.ui.coroutines.CoroutinesViewModel
import cz.airbank.airbanktest.ui.people.PeopleViewModel
import cz.airbank.airbanktest.ui.spacex.SpaceXViewModel
import cz.airbank.airbanktest.ui.states.StatesViewModel
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit


val dataModule = module {
    // TODO
    //  DB
    repositories()
    api()
}

val uiModule = module {
    viewModel { PeopleViewModel() }
    viewModel { CoroutinesViewModel() }
    viewModel { StatesViewModel() }
    // viewModel { SpaceXViewModel(get()) }
    viewModelOf(::SpaceXViewModel)

    // TODO
    //  DBViewModel
}

private fun Module.api() {
    single { createRetrofit() }
    single { get<Retrofit>().create(SpaceXApi::class.java) }
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