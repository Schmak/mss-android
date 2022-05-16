package com.mss.features.series.presentation.mapper

import com.mss.core.utils.Mapper
import com.mss.domain.SeriesItem
import com.mss.features.series.presentation.model.UiSeriesItem

object SeriesItemMapper : Mapper<SeriesItem, UiSeriesItem> {
    override fun SeriesItem.map() =
        UiSeriesItem(
            imageUrl = picture.orEmpty(),
            title = name,
            subtitle = region,
        )
}