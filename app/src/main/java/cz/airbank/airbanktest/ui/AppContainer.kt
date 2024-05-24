package cz.airbank.airbanktest.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cz.airbank.airbanktest.ui.coroutines.CoroutinesScreen
import cz.airbank.airbanktest.ui.home.HomeScreen
import cz.airbank.airbanktest.ui.people.PeopleScreen
import cz.airbank.airbanktest.ui.spacex.SpaceXScreen
import cz.airbank.airbanktest.ui.spacex.detail.RocketDetailScreen
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
                    navigateToSpaceX = { controller.navigate(DestinationSpaceX) }
                )
            }
            composable(DestinationPeople) {
                PeopleScreen()
            }
            composable(DestinationCoroutines) {
                CoroutinesScreen()
            }
            composable(DestinationStates) {
                StatesScreen(
                    onBack = { controller.popBackStack() }
                )
            }
            composable(DestinationSpaceX) {
                SpaceXScreen(
                    onDetailOpen = { rocketId ->
                        controller.navigate(
                            DestinationRocketDetail.replace("{id}", rocketId)
                        )
                    }
                )
            }
            composable(
                route = DestinationRocketDetail,
                arguments = listOf(
                    navArgument("id") { type = NavType.StringType }
                ),
            ) {
                val rocketId = it.arguments?.getString("id").orEmpty() // ""
                RocketDetailScreen(rocketId = rocketId)
            }
        }
    )
}

private const val DestinationHome = "home"
private const val DestinationPeople = "people"
private const val DestinationCoroutines = "coroutines"
private const val DestinationStates = "states"
private const val DestinationSpaceX = "spaceX"
private const val DestinationRocketDetail = "rockets/{id}"