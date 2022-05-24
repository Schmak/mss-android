package com.mss.features.results.presentation.ui.landing.state

import androidx.compose.runtime.Composable
import com.mss.core.ui.model.UiItem

class SessionUiItem(
    override val imageUrl: String,
    title: String,
    override val subtitlesGetters: List<@Composable () -> String?>,
) : UiItem {
    override val getTitle = @Composable { title }
}