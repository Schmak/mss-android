package com.mss.features.driver.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.mss.core.ui.model.common.UiEvent
import com.mss.core.ui.navigation.Route
import com.mss.core.ui.navigation.composable
import com.mss.features.driver.presentation.ui.info.DriverInfoScreen
import com.mss.features.driver.presentation.ui.landing.DriverLandingScreen

fun NavGraphBuilder.driverGraph(
    onUiEvent: (UiEvent) -> Unit
) {
    composable(Route.Drivers) {
        DriverLandingScreen(
            viewModel = hiltViewModel(),
            onUiEvent = onUiEvent
        )
    }
    composable(Route.DriverInfo.INSTANCE) {
        DriverInfoScreen(
            viewModel = hiltViewModel(),
            onUiEvent = onUiEvent
        )
    }
}