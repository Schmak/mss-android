package com.mss.features.series.presentation.mapper

import com.mss.core.domain.SeriesItem
import com.mss.core.ui.model.SimpleUiItem
import com.mss.core.ui.model.UiItem
import com.mss.core.utils.Mapper

object SeriesItemMapper : Mapper<SeriesItem, UiItem> {
    override fun SeriesItem.map() =
        SimpleUiItem(
            imageUrl = picture.orEmpty(),
            title = name,
            subtitles = listOf(region),
        )
}