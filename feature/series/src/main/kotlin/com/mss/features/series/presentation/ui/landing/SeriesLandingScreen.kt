package com.mss.features.series.presentation.ui.landing

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.components.landing.LandingScreen
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.model.common.UiEvent
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.utils.asPageFlow
import com.mss.features.series.presentation.ui.landing.state.SeriesLandingModelState

@Composable
fun SeriesLandingScreen(
    viewModel: SeriesLandingViewModel,
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
fun PreviewSeriesLandingScreen() {
    AppTheme {
        Surface {
            LandingScreen(
                uiState = SeriesLandingModelState(
                    regions = MockSeriesData.regions,
                    categories = MockSeriesData.categories,
                    leadingSeries = MockSeriesData.leadingSeries.asPageFlow(),
                    categorySeries = MockSeriesData.categorySeries.asPageFlow(),
                    regionSeries = MockSeriesData.regionSeries.asPageFlow(),
                    mostRecent = MockSeriesData.mostRecent.asPageFlow(),
                ).toUiState()
            )
        }
    }
}