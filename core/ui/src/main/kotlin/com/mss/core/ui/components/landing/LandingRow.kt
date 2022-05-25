package com.mss.core.ui.components.landing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.model.landing.UiItem
import com.mss.core.ui.model.landing.UiItemConfiguration
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.utils.asPageFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@Composable
fun LandingRow(
    itemsFlow: Flow<PagingData<UiItem>>,
    itemConfig: UiItemConfiguration,
    modifier: Modifier = Modifier,
    onItemClicked: (id: UiItem) -> Unit = {}
) {
    val items = itemsFlow.collectAsLazyPagingItems()
    val loadState = items.loadState.refresh
    val appendState = items.loadState.append
    val state = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    //TODO #11 add load list error handler
    val loading by derivedStateOf { items.itemCount == 0 || loadState is LoadState.Loading }
    LaunchedEffect(key1 = loadState) {
        if (loadState is LoadState.Loading)
            coroutineScope.launch {
                state.animateScrollToItem(0)
            }
    }
    LazyRow(
        state = state,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = !loading,
        modifier = modifier,
    ) {
        if (loading)
            items(Int.MAX_VALUE) {
                Tile(item = null, itemConfig = itemConfig, onClicked = onItemClicked)
            }
        else {
            items(items) {
                Tile(it, itemConfig = itemConfig, onClicked = onItemClicked)
            }
            if (appendState is LoadState.Loading)
                item {
                    Tile(item = null, itemConfig = itemConfig, onClicked = onItemClicked)
                }
        }
    }
}

@MultiPreview
@Composable
fun PreviewLandingRow() {
    AppTheme {
        Surface {
            LandingRow(
                itemsFlow = flowOf(PagingData.from(MockSeriesData.leadingSeries)),
                itemConfig = UiItemConfiguration.NoSubtitle
            )
        }
    }
}

@MultiPreview
@Composable
fun PreviewLandingRowWithSubtitle() {
    AppTheme {
        Surface {
            LandingRow(
                itemsFlow = MockSeriesData.leadingSeries.asPageFlow(),
                itemConfig = UiItemConfiguration.Default
            )
        }
    }
}