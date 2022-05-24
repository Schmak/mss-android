package com.mss.core.ui.model

import com.mss.core.ui.model.UiItemConfiguration.SubtitleColor.Capri
import com.mss.core.ui.model.UiItemConfiguration.SubtitleColor.Cyan

sealed class UiItemConfiguration(
    val subtitles: List<Subtitle>
) {
    object Default : UiItemConfiguration(listOf(Subtitle(Cyan)))
    object NoSubtitle : UiItemConfiguration(emptyList())
    object WithTwoSubtitles : UiItemConfiguration(
        listOf(Subtitle(Capri), Subtitle(Cyan))
    )

    object WithLongSecondSubtitle : UiItemConfiguration(
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