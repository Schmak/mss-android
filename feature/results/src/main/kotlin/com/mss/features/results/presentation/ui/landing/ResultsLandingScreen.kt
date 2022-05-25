package com.mss.features.results.presentation.ui.landing

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.components.landing.LandingScreen
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.data.mock.MockSessionData
import com.mss.core.ui.model.common.UiEvent
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.utils.asPageFlow
import com.mss.features.results.presentation.ui.landing.state.ResultsLandingModelState
import com.mss.features.results.presentation.ui.landing.state.ResultsLandingModelState.Block

@Composable
fun ResultsLandingScreen(
    viewModel: ResultsLandingViewModel,
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
fun PreviewResultsLandingScreen() {
    AppTheme {
        Surface {
            LandingScreen(
                uiState = ResultsLandingModelState(
                    categories = MockSeriesData.categories,
                    mostRecent = Block(MockSessionData.mostRecent.asPageFlow()),
                    mostPopular = Block(MockSessionData.mostPopular.asPageFlow()),
                    forthcoming = Block(MockSessionData.forthcoming.asPageFlow()),
                    categorySessions = Block(MockSessionData.categorySessions.asPageFlow()),
                )
                    .toUiState()
            )
        }
    }
}