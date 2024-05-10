package cz.airbank.airbanktest.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.airbank.airbanktest.ui.coroutines.CoroutinesScreen
import cz.airbank.airbanktest.ui.home.HomeScreen
import cz.airbank.airbanktest.ui.people.PeopleScreen
import cz.airbank.airbanktest.ui.states.StatesScreen

@Composable
fun AppContainer() {
    val controller = rememberNavController()
    NavHost(
        navController = controller,
        startDestination = DestinationHome,
        builder = {
            composable(DestinationHome) {
                HomeScreen(
                    navigateToPeople = { controller.navigate(DestinationPeople) },
                    navigateToCoroutines = { controller.navigate(DestinationCoroutines) },
                    navigateToStates = { controller.navigate(DestinationStates) },
                )
            }
            composable(DestinationPeople) {
                PeopleScreen()
            }
            composable(DestinationCoroutines) {
                CoroutinesScreen()
            }
            composable(DestinationStates) {
                StatesScreen()
            }
        }
    )
}

private const val DestinationHome = "home"
private const val DestinationPeople = "people"
private const val DestinationCoroutines = "coroutines"
private const val DestinationStates = "states"