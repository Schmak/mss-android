package com.mss.core.network.v4.mapper.ref

import com.mss.core.domain.ref.ConstructorReference
import com.mss.core.network.v4.model.ref.ConstructorReferenceDto
import com.mss.core.utils.Mapper

object ConstructorReferenceMapper : Mapper<ConstructorReferenceDto, ConstructorReference> {
    override fun ConstructorReferenceDto.map() = ConstructorReference(
        name = name,
    )
}