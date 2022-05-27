package com.mss.core.network.v4.model

import com.mss.core.network.v4.model.common.ResourceLinkDto
import com.mss.core.network.v4.model.common.create
import com.mss.core.network.v4.model.ref.CountryReferenceDto
import com.mss.core.network.v4.model.ref.create

fun VenueDto.Companion.create(
    name: String = "venue name",
    picture: String? = "venue picture",
    country: CountryReferenceDto? = CountryReferenceDto.create(),
    address: String? = "venue address",
    coordinates: VenueDto.Coordinates? = VenueDto.Coordinates.create(),
    resourceLinks: List<ResourceLinkDto> = listOf(ResourceLinkDto.create())
) = VenueDto(
    name = name,
    picture = picture,
    country = country,
    address = address,
    coordinates = coordinates,
    resourceLinks = resourceLinks
)

fun VenueDto.Coordinates.Companion.create(
    latitude: Double = 50.0,
    longitude: Double = 60.0,
) = VenueDto.Coordinates(
    latitude = latitude,
    longitude = longitude
)