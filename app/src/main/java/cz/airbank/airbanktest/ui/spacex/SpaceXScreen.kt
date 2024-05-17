package cz.airbank.airbanktest.ui.spacex

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cz.airbank.airbanktest.ui.common.StateHost
import org.koin.androidx.compose.koinViewModel

@Composable
fun SpaceXScreen(
    viewModel: SpaceXViewModel = koinViewModel(),
) {
    val state = viewModel.launchState.collectAsState()
    val launches = viewModel.launches.collectAsState()

    StateHost(
        state = state.value,
        onCancel = { /*TODO*/ },
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            items(launches.value) {
                Text(text = it.missionName)
            }
        }
    }
}