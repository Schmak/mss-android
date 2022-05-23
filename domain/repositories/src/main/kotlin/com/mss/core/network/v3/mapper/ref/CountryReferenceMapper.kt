package com.mss.core.network.v3.mapper.ref

import com.mss.core.domain.ref.CountryReference
import com.mss.core.network.v3.model.ref.CountryReferenceDto
import com.mss.core.utils.Mapper

object CountryReferenceMapper : Mapper<CountryReferenceDto, CountryReference> {
    override fun CountryReferenceDto.map() = CountryReference(
        name = name,
        picture = picture,
    )
}