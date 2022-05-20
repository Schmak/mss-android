package com.mss.features.series.presentation.mapper

import com.mss.core.domain.ref.SeriesReference
import com.mss.core.ui.model.UiItem
import com.mss.core.utils.Mapper

object LeadingSeriesItemMapper : Mapper<SeriesReference, UiItem> {
    override fun SeriesReference.map() =
        UiItem(
            imageUrl = picture.orEmpty(),
            title = name,
        )
}