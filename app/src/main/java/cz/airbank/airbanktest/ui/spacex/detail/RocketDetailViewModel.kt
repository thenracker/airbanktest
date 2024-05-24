package cz.airbank.airbanktest.ui.spacex.detail

import cz.airbank.airbanktest.data.dto.RocketDetailTO
import cz.airbank.airbanktest.data.repositories.SpaceXRepo
import cz.airbank.airbanktest.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RocketDetailViewModel(
    private val rocketId: String,
    private val repo: SpaceXRepo,
) : BaseViewModel() {

    private val _detail = MutableStateFlow<RocketDetailTO?>(null)
    val detail = _detail.asStateFlow()

    init {
        fetchDetail(rocketId)
    }

    fun fetchDetail(rocketId: String) {
        launch {
            val detail = repo.fetchRocketDetail(rocketId)
            _detail.emit(detail)
        }
    }
}