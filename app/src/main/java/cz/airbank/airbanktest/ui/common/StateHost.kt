package cz.airbank.airbanktest.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cz.airbank.airbanktest.ui.base.LaunchState
import cz.airbank.airbanktest.ui.theme.AirBankTestTheme

@Composable
fun StateHost(
    state: LaunchState,
    onCancel: () -> Unit,
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

            is LaunchState.Failure -> {
                AlertDialog(
                    onDismissRequest = {},
                    title = { Text(text = "Chyba") },
                    text = { Text(text = state.throwable.message ?: "Neznámá chyba") },
                    confirmButton = {
                        TextButton(onClick = state.retry) {
                            Text(text = "Zkusit znovu")
                        }
                        TextButton(onClick = onCancel) {
                            Text(text = "Zrušit")
                        }
                    },
                )
            }

            LaunchState.None,
            is LaunchState.Success,
            -> {
                content()
            }
        }
    }
}

@Preview
@Composable
private fun PreviewFailure() {
    AirBankTestTheme {
        StateHost(
            state = LaunchState.Failure(
                throwable = Exception(),
                retry = {},
            ),
            onCancel = {},
        ) {}
    }
}

@Preview
@Composable
private fun PreviewLoading() {
    AirBankTestTheme {
        StateHost(
            state = LaunchState.Loading,
            onCancel = {},
        ) {}
    }
}