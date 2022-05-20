package com.mss.features.series.presentation.mapper

import com.mss.core.ui.model.UiItem
import com.mss.core.utils.Mapper
import com.mss.domain.SeriesItem

object SeriesItemMapper : Mapper<SeriesItem, UiItem> {
    override fun SeriesItem.map() =
        UiItem(
            imageUrl = picture.orEmpty(),
            title = name,
            subtitle = region,
        )
}