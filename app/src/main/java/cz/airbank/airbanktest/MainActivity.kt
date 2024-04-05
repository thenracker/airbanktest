package cz.airbank.airbanktest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cz.airbank.airbanktest.ui.HomeScreen
import cz.airbank.airbanktest.ui.PeopleScreen
import cz.airbank.airbanktest.ui.theme.AirBankTestTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AirBankTestTheme {
                // HomeScreen()
                PeopleScreen()
            }
        }
    }
}