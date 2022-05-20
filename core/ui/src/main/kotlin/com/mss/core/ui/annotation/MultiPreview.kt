package com.mss.core.ui.annotation

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview("day")
@Preview("night", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("x1.5", fontScale = 1.5f)
annotation class MultiPreview
