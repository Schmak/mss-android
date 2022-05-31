package com.mss.features.team.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.mss.core.ui.model.common.UiEvent
import com.mss.core.ui.navigation.Route
import com.mss.core.ui.navigation.composable
import com.mss.features.team.presentation.ui.info.TeamInfoScreen
import com.mss.features.team.presentation.ui.landing.TeamLandingScreen

fun NavGraphBuilder.teamGraph(
    onUiEvent: (UiEvent) -> Unit
) {
    composable(Route.Teams) {
        TeamLandingScreen(
            viewModel = hiltViewModel(),
            onUiEvent = onUiEvent
        )
    }
    composable(Route.TeamInfo.INSTANCE) {
        TeamInfoScreen(
            viewModel = hiltViewModel(),
            onUiEvent = onUiEvent
        )
    }
}