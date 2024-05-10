package cz.airbank.airbanktest.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cz.airbank.airbanktest.ui.base.LaunchState

@Composable
fun StateHost(
    state: LaunchState,
    content: @Composable () -> Unit,
) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        when (state) {
            // LaunchState.None -> {}
            LaunchState.Loading -> {
                CircularProgressIndicator()
            }

            is LaunchState.Failure -> TODO()

            LaunchState.None,
            is LaunchState.Success,
            -> {
                content()
            }
        }
    }
}