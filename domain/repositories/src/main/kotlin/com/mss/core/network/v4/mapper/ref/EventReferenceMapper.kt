package com.mss.core.network.v4.mapper.ref

import com.mss.core.domain.ref.EventReference
import com.mss.core.network.v4.model.ref.EventReferenceDto
import com.mss.core.utils.Mapper

object EventReferenceMapper : Mapper<EventReferenceDto, EventReference> {
    override fun EventReferenceDto.map() = EventReference(
        name = name,
        slug = slug,
    )
}