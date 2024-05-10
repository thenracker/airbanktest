package cz.airbank.airbanktest.ui.states

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cz.airbank.airbanktest.ui.common.StateHost
import cz.airbank.airbanktest.ui.theme.AirBankTestTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun StatesScreen(
    viewModel: StatesViewModel = koinViewModel(),
    onBack: () -> Unit,
) {

    val state = viewModel.launchState.collectAsState()

    StateHost(
        state = state.value,
        onCancel = onBack,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Button(onClick = { viewModel.performAction() }) {
                Text(text = "Udělat akci")
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun Preview() {
    AirBankTestTheme {
        StatesScreen(
            onBack = {}
        )
    }
}