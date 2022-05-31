package com.mss.features.series.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.mss.core.ui.model.common.UiEvent
import com.mss.core.ui.navigation.Route
import com.mss.core.ui.navigation.composable
import com.mss.features.series.presentation.ui.info.SeriesInfoScreen
import com.mss.features.series.presentation.ui.landing.SeriesLandingScreen

fun NavGraphBuilder.seriesGraph(
    onUiEvent: (UiEvent) -> Unit
) {
    composable(Route.Series) {
        SeriesLandingScreen(
            viewModel = hiltViewModel(),
            onUiEvent = onUiEvent
        )
    }
    composable(Route.SeriesInfo.INSTANCE) {
        SeriesInfoScreen(
            viewModel = hiltViewModel(),
            onUiEvent = onUiEvent
        )
    }
}