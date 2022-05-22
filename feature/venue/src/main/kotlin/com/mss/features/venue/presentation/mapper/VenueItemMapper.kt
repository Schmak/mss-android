package com.mss.features.venue.presentation.mapper

import com.mss.core.domain.VenueItem
import com.mss.core.ui.model.UiItem
import com.mss.core.utils.Mapper

object VenueItemMapper : Mapper<VenueItem, UiItem> {
    override fun VenueItem.map() =
        UiItem(
            imageUrl = picture.orEmpty(),
            title = name,
            subtitle = country.name,
        )
}