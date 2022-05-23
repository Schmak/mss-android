package com.mss.core.ui.model

import com.mss.core.ui.model.UiItem.Configuration.SubtitleColor.Capri
import com.mss.core.ui.model.UiItem.Configuration.SubtitleColor.Cyan

data class UiItem(
    val imageUrl: String,
    val title: String,
    val subtitles: List<String?> = emptyList(),
) {
    sealed class Configuration(
        val subtitles: List<Subtitle>
    ) {
        object Default : Configuration(listOf(Subtitle(Cyan)))
        object NoSubtitle : Configuration(emptyList())
        object WithTwoSubtitles : Configuration(
            listOf(Subtitle(Capri), Subtitle(Cyan))
        )

        object WithLongSecondSubtitle : Configuration(
            listOf(Subtitle(Capri), Subtitle(Cyan, maxLines = 2))
        )

        data class Subtitle(
            val color: SubtitleColor,
            val maxLines: Int = 1,
        )

        enum class SubtitleColor {
            Cyan,
            Capri
        }
    }
}