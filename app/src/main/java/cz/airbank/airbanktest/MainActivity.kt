package cz.airbank.airbanktest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cz.airbank.airbanktest.ui.AppContainer
import cz.airbank.airbanktest.ui.HomeScreen
import cz.airbank.airbanktest.ui.PeopleScreen
import cz.airbank.airbanktest.ui.theme.AirBankTestTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AirBankTestTheme {

                AppContainer()

                // TODO
                //  Koin - dependency injection
                //  Coroutines, ViewModel

                // TODO
                //  Stahování dat z API
                //    Zobrazení detailu
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