package com.mss.features.series.presentation.ui.landing

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding
import com.mss.core.ui.components.DropdownList
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.theme.Dimensions.Screen
import com.mss.core.ui.theme.divider
import com.mss.features.series.R
import com.mss.features.series.data.mock.MockSeriesData
import com.mss.features.series.presentation.model.UiSeriesItem

@Composable
fun SeriesLandingScreen(
    modifier: Modifier = Modifier
) {
    SeriesLandingScreen(
        leadingSeries = MockSeriesData.leadingSeries,
        categorySeries = MockSeriesData.categorySeries,
        regionSeries = MockSeriesData.regionSeries,
        mostRecent = MockSeriesData.mostRecent,
        categories = MockSeriesData.categories,
        regions = MockSeriesData.regions,
        modifier = modifier
    )
}

@Composable
fun SeriesLandingScreen(
    leadingSeries: List<UiSeriesItem>,
    categorySeries: List<UiSeriesItem>,
    regionSeries: List<UiSeriesItem>,
    mostRecent: List<UiSeriesItem>,
    categories: List<String>,
    regions: List<String>,
    modifier: Modifier = Modifier
) {
    val screenModifier = Modifier.padding(horizontal = Screen.horizontalPadding)
    Column(
        modifier = modifier
            .verticalScroll(
                state = rememberScrollState()
            )
            .navigationBarsPadding()
    ) {
        Text(
            text = stringResource(R.string.series).uppercase(),
            style = MaterialTheme.typography.h1,
            modifier = screenModifier.padding(vertical = 10.dp),
        )
        SeriesList(
            title = stringResource(R.string.leading_series),
            series = leadingSeries,
            modifier = screenModifier,
            firstList = true,
        )
        SeriesList(
            title = stringResource(R.string.categories),
            categories = categories,
            categoryTitle = stringResource(R.string.select_category),
            series = categorySeries,
            modifier = screenModifier,
        )
        SeriesList(
            title = stringResource(R.string.regions),
            categories = regions,
            categoryTitle = stringResource(R.string.select_region),
            series = regionSeries,
            modifier = screenModifier,
        )
        SeriesList(
            title = stringResource(R.string.most_recent),
            series = mostRecent,
            modifier = screenModifier,
        )
    }
}

@Composable
private fun SeriesList(
    title: String,
    categoryTitle: String = "",
    categories: List<String> = emptyList(),
    series: List<UiSeriesItem>,
    modifier: Modifier,
    firstList: Boolean = false,
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
    if (categories.isNotEmpty()) {
        var selectedIdx by remember { mutableStateOf(0) }
        DropdownList(
            title = categoryTitle,
            items = categories,
            initiallyExpanded = false,
            selectedIdx = selectedIdx,
            onItemSelected = { selectedIdx = it },
            modifier = modifier
        )
        Divider(color = MaterialTheme.colors.divider, modifier = dividerModifier)
    }
    LazyRow(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(series) { SeriesTile(it) }
    }
}

@Preview("Series landing screen")
@Preview("Series landing screen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewSeriesScreen() {
    AppTheme {
        Surface {
            SeriesLandingScreen(
                leadingSeries = MockSeriesData.leadingSeries,
                categorySeries = MockSeriesData.categorySeries,
                regionSeries = MockSeriesData.regionSeries,
                mostRecent = MockSeriesData.mostRecent,
                categories = MockSeriesData.categories,
                regions = MockSeriesData.regions,
            )
        }
    }
}