package com.mss.features.series.presentation.ui.landing

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mss.core.ui.components.landing.LandingBlock
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.model.LandingBlockState
import com.mss.core.ui.model.UiItem
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.theme.Dimensions.Screen
import com.mss.core.ui.theme.divider
import com.mss.core.ui.utils.asPageFlow
import com.mss.features.series.R
import com.mss.features.series.presentation.ui.landing.state.SeriesFlows
import com.mss.features.series.presentation.ui.landing.state.SeriesLandingUiState

@Composable
fun SeriesLandingScreen(
    viewModel: SeriesLandingViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    SeriesLandingScreen(
        uiState = uiState,
        onAction = viewModel::handleAction,
        seriesFlows = viewModel,
        modifier = modifier
    )
}

@Composable
fun SeriesLandingScreen(
    uiState: SeriesLandingUiState,
    seriesFlows: SeriesFlows,
    onAction: (UiAction) -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        uiState.hasData ->
            ReadySeriesLandingScreen(
                uiState = uiState,
                onAction = onAction,
                seriesFlows = seriesFlows,
                modifier = modifier,
            )
        uiState.isLoading ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        uiState.errorMessage != null ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = uiState.errorMessage,
                        style = MaterialTheme.typography.body1,
                        maxLines = 1,
                        modifier = Modifier.padding(16.dp)
                    )
                    Button(onClick = { onAction(UiAction.Refresh) }) {
                        Text(text = stringResource(R.string.retry))
                    }
                }
            }
    }
}

@Composable
@Suppress("LongMethod")
private fun ReadySeriesLandingScreen(
    modifier: Modifier,
    uiState: SeriesLandingUiState,
    seriesFlows: SeriesFlows,
    onAction: (UiAction) -> Unit
) {
    val screenModifier = Modifier.padding(horizontal = Screen.horizontalPadding)
    SwipeRefresh(
        state = rememberSwipeRefreshState(uiState.isLoading),
        onRefresh = { onAction(UiAction.Refresh) },
    ) {
        val dividerModifier = modifier.padding(vertical = 10.dp)
        Column(
            modifier = modifier
                .verticalScroll(state = rememberScrollState())
                .navigationBarsPadding()
        ) {
            Text(
                text = stringResource(R.string.series).uppercase(),
                style = MaterialTheme.typography.h1,
                modifier = screenModifier.padding(vertical = 10.dp),
            )
            LandingBlock(
                state = LandingBlockState(
                    titleId = R.string.leading_series,
                    itemsFlow = seriesFlows.leadingSeries,
                    itemsConfig = UiItem.Configuration.NoSubtitle,
                ),
                modifier = screenModifier,
            )
            Divider(color = MaterialTheme.colors.divider, modifier = dividerModifier)
            LandingBlock(
                state = LandingBlockState(
                    titleId = R.string.categories,
                    selector = LandingBlockState.Selector(
                        titleId = R.string.select_category,
                        values = uiState.categories,
                        selectedIdx = uiState.selectedCategoryIdx,
                    ),
                    itemsFlow = seriesFlows.categorySeries,
                ),
                onCategorySelected = { onAction(UiAction.SelectCategory(it)) },
                modifier = screenModifier,
            )
            Divider(color = MaterialTheme.colors.divider, modifier = dividerModifier)
            LandingBlock(
                state = LandingBlockState(
                    titleId = R.string.regions,
                    selector = LandingBlockState.Selector(
                        titleId = R.string.select_region,
                        values = uiState.regions,
                        selectedIdx = uiState.selectedRegionIdx,
                    ),
                    itemsFlow = seriesFlows.regionSeries,
                ),
                onCategorySelected = { onAction(UiAction.SelectRegion(it)) },
                modifier = screenModifier,
            )
            Divider(color = MaterialTheme.colors.divider, modifier = dividerModifier)
            LandingBlock(
                state = LandingBlockState(
                    titleId = R.string.most_recent,
                    itemsFlow = seriesFlows.mostRecent,
                    itemsConfig = UiItem.Configuration.NoSubtitle,
                ),
                modifier = screenModifier,
            )
            Spacer(Modifier.navigationBarsHeight())
        }
    }
}

private val mockSeriesFlows = object : SeriesFlows {
    override val leadingSeries = MockSeriesData.leadingSeries.asPageFlow()
    override val categorySeries = MockSeriesData.categorySeries.asPageFlow()
    override val regionSeries = MockSeriesData.regionSeries.asPageFlow()
    override val mostRecent = MockSeriesData.mostRecent.asPageFlow()
}

@Preview("Series landing screen")
@Preview("Series landing screen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewSeriesScreen() {
    AppTheme {
        Surface {
            SeriesLandingScreen(
                uiState = SeriesLandingUiState(
                    categories = MockSeriesData.categories,
                    regions = MockSeriesData.regions,
                    hasData = true,
                ),
                seriesFlows = mockSeriesFlows,
                onAction = {},
            )
        }
    }
}

@Preview("Loading")
@Preview("Loading (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewLoading() {
    AppTheme {
        Surface {
            SeriesLandingScreen(
                uiState = SeriesLandingUiState(isLoading = true),
                onAction = {},
                seriesFlows = SeriesFlows.Empty,
            )
        }
    }
}

@Preview("Error")
@Preview("Error (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewError() {
    AppTheme {
        Surface {
            SeriesLandingScreen(
                uiState = SeriesLandingUiState(errorMessage = "Some message"),
                onAction = {},
                seriesFlows = SeriesFlows.Empty,
            )
        }
    }
}