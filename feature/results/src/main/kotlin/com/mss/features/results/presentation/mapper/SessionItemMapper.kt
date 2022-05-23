package com.mss.features.results.presentation.mapper

import com.mss.core.domain.SessionItem
import com.mss.core.ui.model.UiItem
import com.mss.core.utils.Mapper

object SessionItemMapper : Mapper<SessionItem, UiItem> {
    override fun SessionItem.map() =
        UiItem(
            imageUrl = series.picture.orEmpty(),
            title = event.name,
            subtitles = listOf(
                name,
                //TODO #30
                startTime.toString()
            ),
        )
}