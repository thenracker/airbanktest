package cz.airbank.airbanktest.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cz.airbank.airbanktest.ui.theme.AirBankTestTheme


@Composable
fun HomeScreen() {
    Column {
        Text(text = "Hello world")
        Text(text = "Hello world")
        Text(text = "Hello world")
        Text(text = "Hello world")
    }
}


@Preview
@Composable
private fun Preview() {
    AirBankTestTheme {
        HomeScreen()
    }
}