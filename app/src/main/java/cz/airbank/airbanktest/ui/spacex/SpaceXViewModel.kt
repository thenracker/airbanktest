package cz.airbank.airbanktest.ui.spacex

import cz.airbank.airbanktest.data.dto.LaunchTO
import cz.airbank.airbanktest.data.repositories.SpaceXRepo
import cz.airbank.airbanktest.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SpaceXViewModel(
    private val spaceXRepo: SpaceXRepo,
) : BaseViewModel() {

    private val _launches = MutableStateFlow<List<LaunchTO>>(emptyList())
    val launches = _launches.asStateFlow()

    init {
        fetchLaunches()
    }

    fun fetchLaunches() {
        launch {
            val launches = spaceXRepo.fetchOrSelectLaunches()
            _launches.emit(launches)
        }
    }
}