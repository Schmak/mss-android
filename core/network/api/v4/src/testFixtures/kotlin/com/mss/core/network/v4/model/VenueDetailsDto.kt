package com.mss.core.network.v4.model

import com.mss.core.network.v4.model.VenueDetailsDto.Circuit
import com.mss.core.network.v4.model.VenueDetailsDto.EventYear
import com.mss.core.network.v4.model.ref.EventReferenceDto
import com.mss.core.network.v4.model.ref.SeriesReferenceDto
import com.mss.core.network.v4.model.ref.create

fun VenueDetailsDto.Companion.create(
    firstEvent: EventYear? = EventYear.create(),
    lastEvent: EventYear? = EventYear.create(),
    lastCircuit: Circuit? = Circuit.create(),
    series: List<SeriesReferenceDto> = listOf(SeriesReferenceDto.create()),
) = VenueDetailsDto(
    firstEvent = firstEvent,
    lastEvent = lastEvent,
    lastCircuit = lastCircuit,
    series = series
)

fun EventYear.Companion.create(
    event: EventReferenceDto = EventReferenceDto.create(),
    year: Int = 2021,
) = EventYear(
    event = event,
    year = year
)

fun Circuit.Companion.create(
    type: String? = "circuit type",
    lengthMeters: Double? = 1250.0,
    totalCorners: Int? = 4,
    picture: String? = "circuit picture",
) = Circuit(
    type = type,
    lengthMeters = lengthMeters,
    totalCorners = totalCorners,
    picture = picture
)