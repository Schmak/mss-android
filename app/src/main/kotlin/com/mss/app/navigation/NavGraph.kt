package com.mss.app.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mss.app.R
import com.mss.app.navigation.Route.Companion.composable
import com.mss.app.ui.series.SeriesScreen

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
        composable(Route.Series) { SeriesScreen() }
        composable(Route.Results) { Text(stringResource(R.string.results)) }
        composable(Route.Drivers) { Text(stringResource(R.string.drivers)) }
        composable(Route.Teams) { Text(stringResource(R.string.teams)) }
        composable(Route.Venues) { Text(stringResource(R.string.venues)) }
    }
}