package com.mss.core.network.v4.mapper.ref

import com.mss.core.domain.ref.CountryReference
import com.mss.core.network.v4.model.ref.CountryReferenceDto
import com.mss.core.utils.Mapper

object CountryReferenceMapper : Mapper<CountryReferenceDto, CountryReference> {
    override fun CountryReferenceDto.map() = CountryReference(
        name = name,
        picture = picture,
    )
}