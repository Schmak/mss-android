package com.mss.core.network.v3.mapper.ref

import com.mss.core.domain.ref.SeriesReference
import com.mss.core.network.v3.model.ref.SeriesReferenceDto
import com.mss.core.utils.Mapper

internal object SeriesReferenceMapper : Mapper<SeriesReferenceDto, SeriesReference> {
    override fun SeriesReferenceDto.map() =
        SeriesReference(
            slug = uuid,
            name = name,
            picture = picture
        )
}