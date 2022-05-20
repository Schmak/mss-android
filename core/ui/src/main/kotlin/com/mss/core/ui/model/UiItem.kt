package com.mss.core.ui.model

data class UiItem(
    val imageUrl: String,
    val title: String,
    val subtitle: String? = null,
) {
    sealed class Configuration(
        val hasSubtitle: Boolean
    ) {
        object Default : Configuration(hasSubtitle = true)
        object NoSubtitle : Configuration(hasSubtitle = false)
    }
}