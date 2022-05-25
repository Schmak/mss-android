package com.mss.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mss.features.driver.presentation.ui.landing.DriverLandingScreen
import com.mss.features.results.presentation.ui.landing.ResultsLandingScreen
import com.mss.features.series.presentation.ui.landing.SeriesLandingScreen
import com.mss.features.team.presentation.ui.landing.TeamLandingScreen
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
        composable(Route.Series) { SeriesLandingScreen(hiltViewModel()) }
        composable(Route.Results) { ResultsLandingScreen(hiltViewModel()) }
        composable(Route.Drivers) { DriverLandingScreen(hiltViewModel()) }
        composable(Route.Teams) { TeamLandingScreen(hiltViewModel()) }
        composable(Route.Venues) { VenueLandingScreen(hiltViewModel()) }
    }
}

private fun NavGraphBuilder.composable(
    route: Route,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = route.value,
        content = content
    )
}