package com.mss.core.network.v3.mapper.ref

import com.mss.core.domain.ref.SeasonReference
import com.mss.core.network.v3.model.ref.SeasonReferenceDto
import com.mss.core.utils.Mapper

internal object SeasonReferenceMapper : Mapper<SeasonReferenceDto, SeasonReference> {
    override fun SeasonReferenceDto.map() =
        SeasonReference(
            slug = uuid,
            name = name,
        )
}