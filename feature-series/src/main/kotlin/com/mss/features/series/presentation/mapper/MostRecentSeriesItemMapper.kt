package com.mss.features.series.presentation.mapper

import com.mss.core.utils.Mapper
import com.mss.domain.SeriesItem
import com.mss.features.series.presentation.model.UiSeriesItem
import java.time.format.DateTimeFormatter

object MostRecentSeriesItemMapper : Mapper<SeriesItem, UiSeriesItem> {
    override fun SeriesItem.map() =
        UiSeriesItem(
            imageUrl = picture.orEmpty(),
            title = lastEvent?.name.orEmpty(),
            subtitle = lastEvent?.date?.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
        )
}