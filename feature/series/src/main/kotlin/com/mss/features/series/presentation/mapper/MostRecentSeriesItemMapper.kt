package com.mss.features.series.presentation.mapper

import com.mss.core.domain.SeriesItem
import com.mss.core.ui.model.UiItem
import com.mss.core.utils.Mapper
import java.time.format.DateTimeFormatter

object MostRecentSeriesItemMapper : Mapper<SeriesItem, UiItem> {
    override fun SeriesItem.map() =
        UiItem(
            imageUrl = picture.orEmpty(),
            title = lastEvent?.name.orEmpty(),
            subtitles = listOf(lastEvent?.date?.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))),
        )
}