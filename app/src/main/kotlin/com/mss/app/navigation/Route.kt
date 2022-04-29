package com.mss.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

sealed class Route(val value: String) {
    object Series : Route("series")
    object Results : Route("results")
    object Drivers : Route("drivers")
    object Teams : Route("teams")
    object Venues : Route("venues")

    companion object {
        fun NavGraphBuilder.composable(
            route: Route,
            content: @Composable (NavBackStackEntry) -> Unit
        ) {
            composable(
                route = route.value,
                content = content
            )
        }
    }
}