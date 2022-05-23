package com.mss.features.results.presentation.ui.landing

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.components.landing.LandingScreen
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.data.mock.MockSessionData
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.utils.asPageFlow
import com.mss.features.results.presentation.ui.landing.state.ResultsLandingModelState

@MultiPreview
@Composable
fun PreviewResultsLandingScreen() {
    AppTheme {
        Surface {
            LandingScreen(
                uiState = ResultsLandingModelState(
                    categories = MockSeriesData.categories,
                    mostRecent = MockSessionData.mostRecent.asPageFlow(),
                    mostPopular = MockSessionData.mostPopular.asPageFlow(),
                    forthcoming = MockSessionData.forthcoming.asPageFlow(),
                    categorySessions = MockSessionData.categorySessions.asPageFlow(),
                )
                    .toUiState()
            )
        }
    }
}