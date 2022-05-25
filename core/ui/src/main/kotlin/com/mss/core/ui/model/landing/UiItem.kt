package com.mss.core.ui.model.landing

import androidx.compose.runtime.Composable

interface UiItem {
    val imageUrl: String
    val getTitle: @Composable () -> String
    val subtitlesGetters: List<@Composable () -> String?>
}