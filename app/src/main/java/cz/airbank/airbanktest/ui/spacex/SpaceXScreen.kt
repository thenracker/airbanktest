package cz.airbank.airbanktest.ui.spacex

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.airbank.airbanktest.ui.common.StateHost
import org.koin.androidx.compose.koinViewModel

@Composable
fun SpaceXScreen(
    viewModel: SpaceXViewModel = koinViewModel(),
    onDetailOpen: (String) -> Unit,
) {
    val state = viewModel.launchState.collectAsState()
    val launches = viewModel.launches.collectAsState()

    StateHost(
        state = state.value,
        onCancel = { /*TODO*/ },
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(launches.value) {
                Card {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onDetailOpen(it.rocket.id)
                            }
                            .padding(16.dp)
                    ) {
                        Text(
                            text = it.missionName + ": " + it.flightNumber,
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    }
                }
            }
        }
    }
}