package com.mss.core.ui.model.landing

import androidx.compose.runtime.Composable
import com.mss.core.ui.navigation.Route

interface UiItem {
    val route: Route?
    val imageUrl: String
    val getTitle: @Composable () -> String
    val subtitlesGetters: List<@Composable () -> String?>
}