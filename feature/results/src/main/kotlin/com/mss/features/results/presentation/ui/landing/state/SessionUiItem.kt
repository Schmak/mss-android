package com.mss.features.results.presentation.ui.landing.state

import androidx.compose.runtime.Composable
import com.mss.core.ui.model.landing.UiItem
import com.mss.core.ui.navigation.Route

class SessionUiItem(
    override val route: Route? = null,
    override val imageUrl: String,
    title: String,
    override val subtitlesGetters: List<@Composable () -> String?>,
) : UiItem {
    override val getTitle = @Composable { title }
}