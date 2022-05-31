package com.mss.features.venue.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.mss.core.ui.model.common.UiEvent
import com.mss.core.ui.navigation.Route
import com.mss.core.ui.navigation.composable
import com.mss.features.venue.presentation.ui.info.VenueInfoScreen
import com.mss.features.venue.presentation.ui.landing.VenueLandingScreen

fun NavGraphBuilder.venueGraph(
    onUiEvent: (UiEvent) -> Unit
) {
    composable(Route.Venues) {
        VenueLandingScreen(
            viewModel = hiltViewModel(),
            onUiEvent = onUiEvent
        )
    }

    composable(Route.VenueInfo.INSTANCE) {
        VenueInfoScreen(
            viewModel = hiltViewModel(),
            onUiEvent = onUiEvent
        )
    }
}