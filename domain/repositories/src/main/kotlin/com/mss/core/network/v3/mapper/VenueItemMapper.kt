package com.mss.core.network.v3.mapper

import com.mss.core.domain.VenueItem
import com.mss.core.network.v3.mapper.ref.CountryReferenceMapper
import com.mss.core.network.v3.model.VenueItemDto
import com.mss.core.utils.Mapper

object VenueItemMapper : Mapper<VenueItemDto, VenueItem> {
    val pageMapper = PageMapper(VenueItemMapper)

    override fun VenueItemDto.map() = VenueItem(
        slug = uuid,
        name = name,
        picture = picture,
        country = country.let(CountryReferenceMapper),
    )
}