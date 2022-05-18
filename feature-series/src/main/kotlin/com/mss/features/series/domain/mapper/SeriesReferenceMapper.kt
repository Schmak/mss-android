package com.mss.features.series.domain.mapper

import com.mss.core.utils.Mapper
import com.mss.domain.ref.SeriesReference
import com.mss.network.model.ref.SeriesReferenceDto

internal object SeriesReferenceMapper : Mapper<SeriesReferenceDto, SeriesReference> {
    override fun SeriesReferenceDto.map() =
        SeriesReference(
            name = name,
            picture = picture
        )
}