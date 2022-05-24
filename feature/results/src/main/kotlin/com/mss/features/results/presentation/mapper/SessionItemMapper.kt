package com.mss.features.results.presentation.mapper

import com.mss.core.domain.SessionItem
import com.mss.core.ui.model.SimpleUiItem
import com.mss.core.ui.model.UiItem
import com.mss.core.utils.Mapper
import java.time.OffsetDateTime

sealed class SessionItemMapper : Mapper<SessionItem, UiItem> {
    protected abstract fun convert(time: OffsetDateTime): String

    override fun SessionItem.map() =
        SimpleUiItem(
            imageUrl = series.picture.orEmpty(),
            title = event.name,
            subtitles = listOf(
                name,
                //TODO #30
                startTime?.let(::convert),
            ),
        )

    object VenueTime : SessionItemMapper() {
        override fun convert(time: OffsetDateTime): String = "Venue: $time"
    }

    object ClientTime : SessionItemMapper() {
        override fun convert(time: OffsetDateTime): String = "Client: $time"
    }
}