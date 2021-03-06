package com.mss.core.ui.components.info

import androidx.annotation.StringRes
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import com.mss.core.ui.R
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.components.common.ListDialog
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.theme.Indigo
import com.mss.core.ui.theme.LightSilver

@Composable
fun ExpandingInfoBlock(
    @StringRes buttonTitleId: Int,
    @StringRes dialogTitleId: Int,
    items: List<String>,
    modifier: Modifier = Modifier,
    onItemClick: (idx: Int) -> Unit = {},
    itemsThreshold: Int = 1,
) {
    if (items.size > itemsThreshold) {
        var openDialog by rememberSaveable { mutableStateOf(false) }
        Button(
            onClick = { openDialog = true },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Indigo,
                disabledBackgroundColor = LightSilver,
            ),
            shape = RectangleShape,
            modifier = modifier,
        ) {
            Text(text = stringResource(id = buttonTitleId).uppercase())
        }
        if (openDialog)
            ListDialog(
                titleId = dialogTitleId,
                items = items,
                onDismissRequest = { openDialog = false },
                onItemClick = onItemClick,
            )
    }
}

@Composable
private fun PreviewBlock(items: List<String>) {
    ExpandingInfoBlock(
        items = items,
        buttonTitleId = R.string.try_again,
        dialogTitleId = R.string.series,
    )
}

@MultiPreview
@Composable
fun PreviewSingleItemBlock() {
    AppTheme {
        Surface {
            PreviewBlock(listOf("abc"))
        }
    }
}

@MultiPreview
@Composable
fun PreviewExpandingInfoBlock() {
    AppTheme {
        Surface {
            PreviewBlock(listOf("abc", "cde"))
        }
    }
}
