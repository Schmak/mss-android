package com.mss.features.series.presentation.mapper

import com.mss.core.ui.model.UiItem
import com.mss.core.utils.Mapper
import com.mss.domain.SeriesItem
import java.time.format.DateTimeFormatter

object MostRecentSeriesItemMapper : Mapper<SeriesItem, UiItem> {
    override fun SeriesItem.map() =
        UiItem(
            imageUrl = picture.orEmpty(),
            title = lastEvent?.name.orEmpty(),
            subtitle = lastEvent?.date?.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
        )
}