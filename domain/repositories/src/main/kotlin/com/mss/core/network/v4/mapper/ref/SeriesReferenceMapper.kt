package com.mss.core.network.v4.mapper.ref

import com.mss.core.domain.ref.SeriesReference
import com.mss.core.network.v4.model.ref.SeriesReferenceDto
import com.mss.core.utils.Mapper

object SeriesReferenceMapper : Mapper<SeriesReferenceDto, SeriesReference> {
    override fun SeriesReferenceDto.map() = SeriesReference(
        name = name,
        picture = picture,
        slug = slug,
    )
}