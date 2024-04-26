package cz.airbank.airbanktest.ui.coroutines

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel


@Composable
fun CoroutinesScreen(
    viewModel: CoroutinesViewModel = koinViewModel(),
) {
    val state = viewModel.state.collectAsState()
    val items = viewModel.items.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = "Launch state",
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = state.value,
            style = MaterialTheme.typography.bodyMedium,
        )
        Button(onClick = { viewModel.exampleCoroutines() }) {
            Text(text = "Zkouška")
        }
        Button(onClick = { viewModel.exampleItems() }) {
            Text(text = "Generovat slova")
        }

        items.value.forEach {
            Text(text = it)
        }
    }
}