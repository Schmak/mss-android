package com.mss.features.driver.presentation.ui.info

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mss.core.ui.R
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.components.info.ExpandingInfoBlock
import com.mss.core.ui.components.info.InfoBlock
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.theme.infoSubtitleColor

@Composable
fun EntityWithPreviousBlock(
    @StringRes titleId: Int,
    @StringRes buttonTitleId: Int,
    items: List<String>,
    modifier: Modifier = Modifier,
) {
    if (items.isEmpty()) return
    Column(modifier) {
        InfoBlock(titleId = titleId, value = items.first())
        if (items.size > 1) {
            Text(
                text = stringResource(R.string.previous),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.infoSubtitleColor,
            )
            Text(
                text = items[1],
                style = MaterialTheme.typography.body1,
            )
        }
        ExpandingInfoBlock(
            buttonTitleId = buttonTitleId,
            dialogTitleId = titleId,
            items = items,
            itemsThreshold = 2
        )
    }
}

@Composable
private fun PreviewEntityWithPreviousBlock(
    items: List<String>
) {
    AppTheme {
        Surface {
            EntityWithPreviousBlock(
                titleId = R.string.series,
                buttonTitleId = R.string.all_series,
                items = items
            )
        }
    }
}

@MultiPreview
@Composable
fun PreviewSingleItemList() {
    PreviewEntityWithPreviousBlock(items = MockSeriesData.names.take(1))
}

@MultiPreview
@Composable
fun PreviewTwoItemsList() {
    PreviewEntityWithPreviousBlock(items = MockSeriesData.names.take(2))
}

@MultiPreview
@Composable
@Suppress("MagicNumber")
fun PreviewThreeItemsList() {
    PreviewEntityWithPreviousBlock(items = MockSeriesData.names.take(3))
}
