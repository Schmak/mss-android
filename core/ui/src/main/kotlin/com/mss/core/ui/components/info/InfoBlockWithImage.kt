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
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.theme.AppTheme

@Composable
fun InfoBlockWithImage(
    @StringRes titleId: Int,
    imageUrl: String?,
    text: String?,
    modifier: Modifier = Modifier
) {
    if (text != null) {
        Column(modifier = modifier) {
            Spacer(modifier = Modifier.height(20.dp))
            BlockHeader(titleId = titleId)
            TextRowWithImage(text = text, imageUrl = imageUrl)
        }
    }
}

@MultiPreview
@Composable
fun PreviewInfoBlockWithImage() {
    AppTheme {
        Surface {
            InfoBlockWithImage(
                titleId = R.string.categories,
                text = "Stock car",
                imageUrl = MockSeriesData.pictures.first()
            )
        }
    }
}