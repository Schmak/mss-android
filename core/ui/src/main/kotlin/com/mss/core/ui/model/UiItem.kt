package com.mss.core.ui.model

data class UiItem(
    val imageUrl: String,
    val title: String,
    val subtitles: List<String?> = emptyList(),
)