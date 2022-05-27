package com.mss.features.venue.presentation.mapper

import com.mss.core.domain.VenueItem
import com.mss.core.ui.model.landing.SimpleUiItem
import com.mss.core.ui.model.landing.UiItem
import com.mss.core.ui.navigation.Route
import com.mss.core.utils.Mapper

object VenueItemMapper : Mapper<VenueItem, UiItem> {
    override fun VenueItem.map() =
        SimpleUiItem(
            route = Route.VenueInfo(slug),
            imageUrl = picture.orEmpty(),
            title = name,
            subtitles = listOf(country.name),
        )
}