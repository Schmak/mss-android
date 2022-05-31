package com.mss.core.network.v3.mapper.ref

import com.mss.core.domain.ref.DriverReference
import com.mss.core.network.v3.model.ref.DriverReferenceDto
import com.mss.core.utils.Mapper

object DriverReferenceMapper : Mapper<DriverReferenceDto, DriverReference> {
    override fun DriverReferenceDto.map() = DriverReference(
        name = name,
        picture = picture,
        slug = uuid,
        countryFlag = null,
    )
}