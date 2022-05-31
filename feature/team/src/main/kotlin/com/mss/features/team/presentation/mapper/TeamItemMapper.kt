package com.mss.features.team.presentation.mapper

import com.mss.core.domain.TeamItem
import com.mss.core.ui.model.landing.SimpleUiItem
import com.mss.core.ui.model.landing.UiItem
import com.mss.core.ui.navigation.Route
import com.mss.core.utils.Mapper

object TeamItemMapper : Mapper<TeamItem, UiItem> {
    override fun TeamItem.map() =
        SimpleUiItem(
            route = Route.TeamInfo(slug),
            imageUrl = picture.orEmpty(),
            title = name,
            subtitles = listOf(country?.name.orEmpty()),
        )
}