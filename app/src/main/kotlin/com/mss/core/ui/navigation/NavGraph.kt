package com.mss.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mss.core.ui.model.common.UiEvent
import com.mss.features.driver.presentation.ui.info.DriverInfoScreen
import com.mss.features.driver.presentation.ui.landing.DriverLandingScreen
import com.mss.features.results.presentation.ui.landing.ResultsLandingScreen
import com.mss.features.series.presentation.ui.info.SeriesInfoScreen
import com.mss.features.series.presentation.ui.landing.SeriesLandingScreen
import com.mss.features.team.presentation.ui.landing.TeamLandingScreen
import com.mss.features.venue.presentation.ui.info.VenueInfoScreen
import com.mss.features.venue.presentation.ui.landing.VenueLandingScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: Route = Route.Series,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.value,
        modifier = modifier
    ) {
        composable(Route.Series) {
            SeriesLandingScreen(
                viewModel = hiltViewModel(),
                onUiEvent = navController::onUiEvent
            )
        }
        composable(Route.Results) {
            ResultsLandingScreen(
                viewModel = hiltViewModel(),
                onUiEvent = navController::onUiEvent
            )
        }
        composable(Route.Drivers) {
            DriverLandingScreen(
                viewModel = hiltViewModel(),
                onUiEvent = navController::onUiEvent
            )
        }
        composable(Route.Teams) {
            TeamLandingScreen(
                viewModel = hiltViewModel(),
                onUiEvent = navController::onUiEvent
            )
        }
        composable(Route.Venues) {
            VenueLandingScreen(
                viewModel = hiltViewModel(),
                onUiEvent = navController::onUiEvent
            )
        }
        composable(Route.SeriesInfo.INSTANCE) {
            SeriesInfoScreen(
                viewModel = hiltViewModel(),
                onUiEvent = navController::onUiEvent
            )
        }
        composable(Route.VenueInfo.INSTANCE) {
            VenueInfoScreen(
                viewModel = hiltViewModel(),
                onUiEvent = navController::onUiEvent
            )
        }
        composable(Route.DriverInfo.INSTANCE) {
            DriverInfoScreen(
                viewModel = hiltViewModel(),
                onUiEvent = navController::onUiEvent
            )
        }
    }
}

private fun NavHostController.onUiEvent(event: UiEvent) = when (event) {
    is UiEvent.Navigate -> navigate(event.route.value)
}

private fun NavGraphBuilder.composable(
    route: Route,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = route.value,
        arguments = route.arguments.map { navArgument(it) {} },
        content = content
    )
}