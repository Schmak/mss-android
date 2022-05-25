package com.mss.features.driver.presentation.ui.landing

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.components.landing.LandingScreen
import com.mss.core.ui.data.mock.MockDriverData
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.model.common.UiEvent
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.utils.asPageFlow
import com.mss.features.driver.presentation.ui.landing.state.DriverLandingModelState

@Composable
fun DriverLandingScreen(
    viewModel: DriverLandingViewModel,
    modifier: Modifier = Modifier,
    onUiEvent: (UiEvent) -> Unit = {},
) {
    LaunchedEffect(key1 = true) { viewModel.uiEvents.collect(onUiEvent) }
    val uiState by viewModel.uiState.collectAsState()
    LandingScreen(
        uiState = uiState,
        onAction = viewModel::handleAction,
        modifier = modifier
    )
}

@MultiPreview
@Composable
fun PreviewDriverLandingScreen() {
    AppTheme {
        Surface {
            LandingScreen(
                uiState = DriverLandingModelState(
                    goldenSeries = MockSeriesData.goldenSeries,
                    collections = Collections.values().toList(),
                    currentSeasonDrivers = MockDriverData.currentSeasonDrivers.asPageFlow(),
                    champions = MockDriverData.champions.asPageFlow(),
                    winners = MockDriverData.winners.asPageFlow(),
                    collectionDrivers = MockDriverData.collectionDrivers.asPageFlow(),
                )
                    .toUiState()
            )
        }
    }
}