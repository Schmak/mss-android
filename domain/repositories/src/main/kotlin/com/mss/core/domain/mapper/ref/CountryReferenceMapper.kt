package com.mss.core.domain.mapper.ref

import com.mss.core.domain.ref.CountryReference
import com.mss.core.utils.Mapper
import com.mss.network.model.ref.CountryReferenceDto

object CountryReferenceMapper : Mapper<CountryReferenceDto, CountryReference> {
    override fun CountryReferenceDto.map() = CountryReference(
        name = name,
        picture = picture,
    )
}