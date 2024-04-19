package cz.airbank.airbanktest.di

import cz.airbank.airbanktest.ui.people.PeopleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val dataModule = module {
    // TODO
    //  DB
    //  API
    //  repositories()
}

val uiModule = module {

    viewModel { PeopleViewModel() }

    // TODO
    //  ApiViewModel
    //  DBViewModel
}