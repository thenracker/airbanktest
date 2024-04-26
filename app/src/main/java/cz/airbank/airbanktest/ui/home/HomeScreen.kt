package cz.airbank.airbanktest.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.airbank.airbanktest.ui.theme.AirBankTestTheme


@Composable
fun HomeScreen(
    navigateToPeople: () -> Unit,
    navigateToCoroutines: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Button(onClick = navigateToPeople) {
            Text(text = "Seznam lidí (bez databáze)")
        }
        Button(onClick = navigateToCoroutines) {
            Text(text = "Coroutines obrazovka")
        }
    }
}


@Preview
@Composable
private fun Preview() {
    AirBankTestTheme {
        HomeScreen(
            navigateToPeople = {},
            navigateToCoroutines = {},
        )
    }
}