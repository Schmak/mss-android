package com.mss.network.model

import com.mss.network.model.ref.CountryReferenceDto
import com.mss.network.model.ref.EventReferenceDto
import com.mss.network.model.ref.create

fun VenueItemDto.Companion.create(
    uuid: String = "Venue uuid",
    name: String = "Venue name",
    country: CountryReferenceDto = CountryReferenceDto.create(),
    lastEvent: EventReferenceDto? = EventReferenceDto.create(),
    lastEventDate: Long? = 1234L,
    picture: String? = "Venue picture",
) = VenueItemDto(
    uuid = uuid,
    name = name,
    country = country,
    lastEvent = lastEvent,
    lastEventDate = lastEventDate,
    picture = picture
)