package com.mss.core.domain.mapper.ref

import com.mss.core.domain.ref.SeasonReference
import com.mss.core.utils.Mapper
import com.mss.network.model.ref.SeasonReferenceDto

internal object SeasonReferenceMapper : Mapper<SeasonReferenceDto, SeasonReference> {
    override fun SeasonReferenceDto.map() =
        SeasonReference(
            slug = uuid,
            name = name,
        )
}