package com.mss.core.domain.mapper

import com.mss.core.domain.VenueItem
import com.mss.core.domain.mapper.ref.CountryReferenceMapper
import com.mss.core.utils.Mapper
import com.mss.network.model.VenueItemDto

object VenueItemMapper : Mapper<VenueItemDto, VenueItem> {
    val pageMapper = PageMapper(VenueItemMapper)

    override fun VenueItemDto.map() = VenueItem(
        slug = uuid,
        name = name,
        picture = picture,
        country = country.let(CountryReferenceMapper),
    )
}