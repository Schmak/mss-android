package com.mss.features.venue.presentation.ui.landing

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.components.landing.LandingScreen
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.data.mock.MockVenueData
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.utils.asPageFlow
import com.mss.features.venue.presentation.ui.landing.state.VenueLandingModelState

@Composable
fun VenueLandingScreen(
    viewModel: VenueLandingViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    LandingScreen(
        uiState = uiState,
        onAction = viewModel::handleAction,
        modifier = modifier
    )
}

@MultiPreview
@Composable
fun PreviewVenueLandingScreen() {
    AppTheme {
        Surface {
            LandingScreen(
                uiState = VenueLandingModelState(
                    goldenSeries = MockSeriesData.goldenSeries,
                    currentSeasonVenues = MockVenueData.currentSeasonVenues.asPageFlow(),
                    raceCircuitVenues = MockVenueData.raceCircuitVenues.asPageFlow(),
                    rallycrossVenues = MockVenueData.rallycrossVenues.asPageFlow(),
                    roadCircuitVenues = MockVenueData.roadCircuitVenues.asPageFlow(),
                    streetCircuitVenues = MockVenueData.streetCircuitVenues.asPageFlow(),
                )
                    .toUiState()
            )
        }
    }
}