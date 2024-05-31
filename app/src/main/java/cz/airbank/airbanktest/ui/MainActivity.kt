package cz.airbank.airbanktest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cz.airbank.airbanktest.ui.AppContainer
import cz.airbank.airbanktest.ui.theme.AirBankTestTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AirBankTestTheme {

                AppContainer()

                // TODO
                //  Databáze lokální
                //  DataStore

                // TODO
                //  Notifikace
                //  Senzory
                //  Mapy...
            }
        }
    }
}