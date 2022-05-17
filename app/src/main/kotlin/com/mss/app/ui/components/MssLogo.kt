package com.mss.app.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mss.app.R
import com.mss.core.ui.theme.AppTheme

@Composable
fun MssLogo(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.mss_logo),
        contentScale = ContentScale.Fit,
        alignment = Alignment.CenterStart,
        contentDescription = null,
        modifier = modifier
    )
}

@Preview("MSS logo (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewMssLogo() {
    AppTheme {
        Surface {
            MssLogo()
        }
    }
}