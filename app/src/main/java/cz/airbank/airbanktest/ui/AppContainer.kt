package cz.airbank.airbanktest.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.airbank.airbanktest.ui.home.HomeScreen
import cz.airbank.airbanktest.ui.people.PeopleScreen

@Composable
fun AppContainer() {
    val controller = rememberNavController()
    NavHost(
        navController = controller,
        startDestination = DestinationHome,
        builder = {

            composable(DestinationHome) {
                HomeScreen(
                    navigateToPeople = {
                        controller.navigate(DestinationPeople)
                    }
                )
            }

            composable(DestinationPeople) {
                PeopleScreen()
            }

        }
    )
}

private const val DestinationHome = "home"
private const val DestinationPeople = "people"