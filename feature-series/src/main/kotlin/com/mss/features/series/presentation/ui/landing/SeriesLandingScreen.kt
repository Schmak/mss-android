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
import androidx.paging.PagingData
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mss.core.ui.components.DropdownList
import com.mss.core.ui.components.landing.LandingRow
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.model.UiItem
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.theme.Dimensions.Screen
import com.mss.core.ui.theme.divider
import com.mss.core.ui.utils.asPageFlow
import com.mss.features.series.R
import com.mss.features.series.presentation.ui.landing.state.SeriesFlows
import com.mss.features.series.presentation.ui.landing.state.SeriesLandingUiState
import kotlinx.coroutines.flow.Flow

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
            SeriesList(
                title = stringResource(R.string.leading_series),
                seriesFlow = seriesFlows.leadingSeries,
                modifier = screenModifier,
                firstList = true, itemConfig = UiItem.Configuration.NoSubtitle,
            )
            SeriesList(
                title = stringResource(R.string.categories),
                categories = {
                    CategoryList(
                        categoryTitle = stringResource(R.string.select_category),
                        categories = uiState.categories,
                        selectedCategoryIdx = uiState.selectedCategoryIdx,
                        onCategorySelected = { onAction(UiAction.SelectCategory(it)) },
                        modifier = screenModifier,
                    )
                },
                seriesFlow = seriesFlows.categorySeries,
                modifier = screenModifier,
            )
            SeriesList(
                title = stringResource(R.string.regions),
                categories = {
                    CategoryList(
                        categoryTitle = stringResource(R.string.select_region),
                        categories = uiState.regions,
                        selectedCategoryIdx = uiState.selectedRegionIdx,
                        onCategorySelected = { onAction(UiAction.SelectRegion(it)) },
                        modifier = screenModifier,
                    )
                },
                seriesFlow = seriesFlows.regionSeries,
                modifier = screenModifier,
            )
            SeriesList(
                title = stringResource(R.string.most_recent),
                seriesFlow = seriesFlows.mostRecent,
                modifier = screenModifier,
            )
            Spacer(Modifier.navigationBarsHeight())
        }
    }
}

@Composable
private fun CategoryList(
    categoryTitle: String = "",
    categories: List<String> = emptyList(),
    selectedCategoryIdx: Int = 0,
    onCategorySelected: (Int) -> Unit = {},
    modifier: Modifier
) {
    DropdownList(
        title = categoryTitle,
        items = categories,
        initiallyExpanded = false,
        selectedIdx = selectedCategoryIdx,
        onItemSelected = onCategorySelected,
        modifier = modifier
    )
    Divider(color = MaterialTheme.colors.divider, modifier = modifier.padding(vertical = 10.dp))
}

@Composable
private fun SeriesList(
    title: String,
    categories: @Composable () -> Unit = {},
    seriesFlow: Flow<PagingData<UiItem>>,
    modifier: Modifier,
    firstList: Boolean = false,
    itemConfig: UiItem.Configuration = UiItem.Configuration.Default,
) {
    val dividerModifier = modifier.padding(vertical = 10.dp)
    if (!firstList)
        Divider(color = MaterialTheme.colors.divider, modifier = dividerModifier)
    Text(
        text = title,
        style = MaterialTheme.typography.h2,
        modifier = modifier
    )
    Divider(color = MaterialTheme.colors.divider, modifier = dividerModifier)
    categories()
    LandingRow(itemsFlow = seriesFlow, itemConfig = itemConfig)
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