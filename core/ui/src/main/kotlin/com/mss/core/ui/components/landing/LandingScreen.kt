package com.mss.core.ui.components.landing

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.mss.core.ui.R
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.model.LandingBlockState
import com.mss.core.ui.model.LandingUiState
import com.mss.core.ui.model.UiItemConfiguration.NoSubtitle
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.theme.Dimensions.Screen
import com.mss.core.ui.theme.divider
import com.mss.core.ui.utils.asPageFlow

@Composable
fun LandingScreen(
    uiState: LandingUiState,
    modifier: Modifier = Modifier,
    onAction: (UiAction) -> Unit = {},
) {
    when {
        uiState.hasData ->
            ReadyLandingScreen(
                uiState = uiState,
                onAction = onAction,
                modifier = modifier,
            )
        uiState.isLoading ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
            }
        uiState.errorMessageId != null ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = stringResource(uiState.errorMessageId),
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
private fun ReadyLandingScreen(
    modifier: Modifier,
    uiState: LandingUiState,
    onAction: (UiAction) -> Unit
) {
    val screenModifier = modifier.padding(horizontal = Screen.horizontalPadding)
    SwipeRefresh(
        state = rememberSwipeRefreshState(uiState.isLoading),
        onRefresh = { onAction(UiAction.Refresh) },
    ) {
        LazyColumn {
            item {
                Text(
                    text = stringResource(uiState.titleId).uppercase(),
                    style = MaterialTheme.typography.h1,
                    modifier = screenModifier.padding(vertical = 10.dp),
                    maxLines = 1,
                )
            }
            items(uiState.blocks.size) { idx ->
                val block = uiState.blocks[idx]
                LandingBlock(
                    state = block,
                    onCategorySelected = {
                        onAction(
                            UiAction.SelectCategory(
                                blockId = block.selector?.id,
                                idx = it
                            )
                        )
                    },
                    onActionClicked = {
                        onAction(UiAction.ActionButtonClick(blockId = block.action?.id))
                    },
                    modifier = screenModifier,
                )
                if (idx != uiState.blocks.lastIndex)
                    Divider(
                        color = MaterialTheme.colors.divider,
                        modifier = screenModifier.padding(vertical = 10.dp)
                    )
            }
        }
        Spacer(Modifier.navigationBarsHeight())
    }
}

@MultiPreview
@Composable
fun PreviewLandingScreen() {
    AppTheme {
        Surface {
            LandingScreen(
                uiState = LandingUiState(
                    titleId = R.string.series,
                    blocks = listOf(
                        LandingBlockState(
                            titleId = R.string.categories,
                            selector = null,
                            action = LandingBlockState.Action(
                                drawableId = R.drawable.ic_mss,
                                descriptionId = R.string.select_series,
                            ),
                            itemsFlow = MockSeriesData.leadingSeries.asPageFlow(),
                            itemsConfig = NoSubtitle,
                        ),
                        LandingBlockState(
                            titleId = R.string.categories,
                            selector = LandingBlockState.Selector(
                                titleId = R.string.series,
                                values = MockSeriesData.regions,
                            ),
                            itemsFlow = MockSeriesData.regionSeries.asPageFlow(),
                            itemsConfig = NoSubtitle,
                        ),
                    ),
                    hasData = true,
                ),
                onAction = {},
            )
        }
    }
}

@MultiPreview
@Composable
fun PreviewLoading() {
    AppTheme {
        Surface {
            LandingScreen(
                uiState = LandingUiState(isLoading = true, titleId = R.string.retry),
                onAction = {},
            )
        }
    }
}

@MultiPreview
@Composable
fun PreviewError() {
    AppTheme {
        Surface {
            LandingScreen(
                uiState = LandingUiState(
                    errorMessageId = R.string.try_again,
                    titleId = R.string.retry
                ),
                onAction = {},
            )
        }
    }
}