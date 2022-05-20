package com.mss.features.series.presentation.mapper

import com.mss.core.ui.model.UiItem
import com.mss.core.utils.Mapper
import com.mss.domain.ref.SeriesReference

object LeadingSeriesItemMapper : Mapper<SeriesReference, UiItem> {
    override fun SeriesReference.map() =
        UiItem(
            imageUrl = picture.orEmpty(),
            title = name,
        )
}