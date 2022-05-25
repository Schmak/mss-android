package com.mss.features.series.presentation.mapper

import com.mss.core.domain.ref.SeriesReference
import com.mss.core.ui.model.landing.SimpleUiItem
import com.mss.core.ui.model.landing.UiItem
import com.mss.core.utils.Mapper

object LeadingSeriesItemMapper : Mapper<SeriesReference, UiItem> {
    override fun SeriesReference.map() =
        SimpleUiItem(
            imageUrl = picture.orEmpty(),
            title = name,
        )
}