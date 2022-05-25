package com.mss.core.ui.model.landing

import androidx.compose.runtime.Composable

data class SimpleUiItem(
    override val imageUrl: String,
    val title: String,
    val subtitles: List<String?> = emptyList()
) : UiItem {
    override val getTitle = @Composable { title }
    override val subtitlesGetters: List<@Composable () -> String?> =
        subtitles.map { @Composable { it } }
}