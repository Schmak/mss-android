package com.mss.features.series.presentation.mapper

import com.mss.core.utils.Mapper
import com.mss.domain.ref.SeriesReference
import com.mss.features.series.presentation.model.UiSeriesItem

object LeadingSeriesItemMapper : Mapper<SeriesReference, UiSeriesItem> {
    override fun SeriesReference.map() =
        UiSeriesItem(
            imageUrl = picture.orEmpty(),
            title = name,
        )
}