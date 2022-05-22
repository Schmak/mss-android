package com.mss.core.ui.model

import com.mss.core.ui.model.UiItem.Configuration.SubtitleColor.Capri
import com.mss.core.ui.model.UiItem.Configuration.SubtitleColor.Cyan

data class UiItem(
    val imageUrl: String,
    val title: String,
    val subtitles: List<String?> = emptyList(),
) {
    sealed class Configuration(
        val subtitles: List<SubtitleColor>
    ) {
        object Default : Configuration(listOf(Cyan))
        object NoSubtitle : Configuration(emptyList())
        object WithTwoSubtitles : Configuration(listOf(Capri, Cyan))

        enum class SubtitleColor {
            Cyan,
            Capri
        }
    }
}