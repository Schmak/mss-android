package com.mss.core.ui.components.landing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mss.core.ui.R
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.components.DropdownList
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.model.landing.LandingBlockState
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.theme.divider
import com.mss.core.ui.utils.asPageFlow

@Composable
fun LandingBlock(
    state: LandingBlockState,
    modifier: Modifier = Modifier,
    onCategorySelected: (Int) -> Unit = {},
    onActionClicked: () -> Unit = {},
) {
    val dividerModifier = modifier.padding(vertical = 10.dp)
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            Text(
                text = stringResource(state.titleId),
                style = MaterialTheme.typography.h2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f, fill = false)
            )
            if (state.action != null)
                IconButton(
                    onClick = onActionClicked,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(state.action.drawableId),
                        contentDescription = stringResource(state.action.descriptionId),
                    )
                }
        }
        Divider(color = MaterialTheme.colors.divider, modifier = dividerModifier)
        if (state.selector != null) {
            DropdownList(
                title = stringResource(state.selector.titleId),
                items = state.selector.values,
                initiallyExpanded = false,
                selectedIdx = state.selector.selectedIdx,
                onItemSelected = onCategorySelected,
                modifier = modifier
            )
            Divider(color = MaterialTheme.colors.divider, modifier = dividerModifier)
        }
        LandingRow(itemsFlow = state.itemsFlow, itemConfig = state.itemsConfig)
    }
}

@MultiPreview
@Composable
fun PreviewWithoutSelector() {
    AppTheme {
        Surface {
            LandingBlock(
                state = LandingBlockState(
                    titleId = R.string.series,
                    selector = null,
                    action = LandingBlockState.Action(
                        drawableId = R.drawable.ic_mss,
                        descriptionId = R.string.select_series,
                    ),
                    itemsFlow = MockSeriesData.leadingSeries.asPageFlow(),
                ),
                onCategorySelected = {}
            )
        }
    }
}

@MultiPreview
@Composable
fun PreviewWithSelector() {
    AppTheme {
        Surface {
            LandingBlock(
                state = LandingBlockState(
                    titleId = R.string.series,
                    selector = LandingBlockState.Selector(
                        titleId = R.string.select_series,
                        values = MockSeriesData.categories,
                    ),
                    itemsFlow = MockSeriesData.leadingSeries.asPageFlow(),
                ),
                onCategorySelected = {}
            )
        }
    }
}