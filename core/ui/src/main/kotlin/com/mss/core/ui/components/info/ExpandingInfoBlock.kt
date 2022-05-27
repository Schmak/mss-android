package com.mss.core.ui.components.info

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
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
import com.mss.core.ui.theme.infoSubtitleColor

@Composable
fun ExpandingInfoBlock(
    @StringRes buttonTitleId: Int,
    @StringRes dialogTitleId: Int,
    items: List<String>,
    modifier: Modifier = Modifier,
    onItemClick: (idx: Int) -> Unit = {}
) {
    if (items.isEmpty()) return
    Column(modifier = modifier) {
        Text(
            text = items.first(),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.infoSubtitleColor,
            modifier = Modifier.clickable { onItemClick(0) }
        )
        if (items.size > 1) {
            var openDialog by rememberSaveable { mutableStateOf(false) }
            Button(
                onClick = { openDialog = true },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Indigo,
                    disabledBackgroundColor = LightSilver,
                ),
                shape = RectangleShape,
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
