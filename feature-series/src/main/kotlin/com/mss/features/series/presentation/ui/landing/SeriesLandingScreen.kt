package com.mss.features.series.presentation.ui.landing

import android.content.res.Configuration
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mss.core.ui.components.landing.LandingScreen
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.utils.asPageFlow
import com.mss.features.series.presentation.ui.landing.state.SeriesLandingModelState

@Composable
fun SeriesLandingScreen(
    viewModel: SeriesLandingViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    LandingScreen(
        uiState = uiState,
        onAction = viewModel::handleAction,
        modifier = modifier
    )
}

@Preview("Series landing screen")
@Preview("Series landing screen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
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