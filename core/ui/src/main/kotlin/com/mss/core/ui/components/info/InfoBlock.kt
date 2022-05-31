package com.mss.core.ui.components.info

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mss.core.ui.R
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.theme.AppTheme

@Composable
fun InfoBlock(
    @StringRes titleId: Int,
    value: String?,
    modifier: Modifier = Modifier
) {
    if (value != null) {
        Column(modifier = modifier) {
            Spacer(modifier = Modifier.height(20.dp))
            BlockHeader(titleId = titleId)
            TextRow(value)
        }
    }
}

@MultiPreview
@Composable
fun PreviewInfoBlock() {
    AppTheme {
        Surface {
            InfoBlock(titleId = R.string.categories, value = "Stock car")
        }
    }
}