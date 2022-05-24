package com.mss.core.ui.model

data class SimpleUiItem(
    override val imageUrl: String,
    override val title: String,
    override val subtitles: List<String?> = emptyList()
) : UiItem