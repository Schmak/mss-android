package com.mss.core.network.v4.mapper

import com.mss.core.domain.Venue
import com.mss.core.network.v4.mapper.common.ResourceLinkMapper
import com.mss.core.network.v4.mapper.ref.CountryReferenceMapper
import com.mss.core.network.v4.model.VenueDto
import com.mss.core.utils.Mapper

object VenueMapper : Mapper<VenueDto, Venue> {
    override fun VenueDto.map() = Venue(
        name = name,
        picture = picture,
        country = country?.let(CountryReferenceMapper),
        address = address,
        resourceLinks = resourceLinks.mapNotNull(ResourceLinkMapper)
    )
}