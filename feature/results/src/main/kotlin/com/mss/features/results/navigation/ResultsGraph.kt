package com.mss.features.results.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.mss.core.ui.model.common.UiEvent
import com.mss.core.ui.navigation.Route
import com.mss.core.ui.navigation.composable
import com.mss.features.results.presentation.ui.landing.ResultsLandingScreen

fun NavGraphBuilder.resultsGraph(
    onUiEvent: (UiEvent) -> Unit
) {
    composable(Route.Results) {
        ResultsLandingScreen(
            viewModel = hiltViewModel(),
            onUiEvent = onUiEvent
        )
    }
}