package com.mss.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mss.core.ui.model.common.UiEvent
import com.mss.features.driver.navigation.driverGraph
import com.mss.features.results.navigation.resultsGraph
import com.mss.features.series.navigation.seriesGraph
import com.mss.features.team.navigation.teamGraph
import com.mss.features.venue.navigation.venueGraph

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
        seriesGraph(navController::onUiEvent)
        driverGraph(navController::onUiEvent)
        resultsGraph(navController::onUiEvent)
        teamGraph(navController::onUiEvent)
        venueGraph(navController::onUiEvent)
    }
}

private fun NavHostController.onUiEvent(event: UiEvent) = when (event) {
    is UiEvent.Navigate -> navigate(event.route.value)
}