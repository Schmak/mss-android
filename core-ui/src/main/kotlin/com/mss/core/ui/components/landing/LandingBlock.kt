package com.mss.core.ui.components.landing

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mss.core.ui.R
import com.mss.core.ui.components.DropdownList
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.model.LandingBlockState
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.theme.divider
import com.mss.core.ui.utils.asPageFlow

@Composable
fun LandingBlock(
    state: LandingBlockState,
    modifier: Modifier = Modifier,
    onCategorySelected: (Int) -> Unit = {},
) {
    val dividerModifier = modifier.padding(vertical = 10.dp)
    Column {
        Text(
            text = stringResource(state.titleId),
            style = MaterialTheme.typography.h2,
            modifier = modifier
        )
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

@Preview
@Preview("dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewWithoutSelector() {
    AppTheme {
        Surface {
            LandingBlock(
                state = LandingBlockState(
                    titleId = R.string.retry,
                    selector = null,
                    itemsFlow = MockSeriesData.leadingSeries.asPageFlow(),
                ),
                onCategorySelected = {}
            )
        }
    }
}

@Preview
@Preview("dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewWithSelector() {
    AppTheme {
        Surface {
            LandingBlock(
                state = LandingBlockState(
                    titleId = R.string.retry,
                    selector = LandingBlockState.Selector(
                        titleId = R.string.retry,
                        values = MockSeriesData.categories,
                    ),
                    itemsFlow = MockSeriesData.leadingSeries.asPageFlow(),
                ),
                onCategorySelected = {}
            )
        }
    }
}