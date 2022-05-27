package com.mss.core.domain

import com.mss.core.domain.VenueDetails.Circuit
import com.mss.core.domain.VenueDetails.EventYear
import com.mss.core.domain.ref.EventReference
import com.mss.core.domain.ref.SeriesReference
import com.mss.core.domain.ref.create

fun VenueDetails.Companion.create(
    firstEvent: EventYear? = EventYear.create(),
    lastEvent: EventYear? = EventYear.create(),
    lastCircuit: Circuit? = Circuit.create(),
    series: List<SeriesReference> = listOf(SeriesReference.create()),
) = VenueDetails(
    firstEvent = firstEvent,
    lastEvent = lastEvent,
    lastCircuit = lastCircuit,
    series = series
)

fun EventYear.Companion.create(
    event: EventReference = EventReference.create(),
    year: Int = 2020,
) = EventYear(
    event = event,
    year = year
)

fun Circuit.Companion.create(
    lengthMeters: Double? = 1545.0,
    totalCorners: Int? = 8,
) = Circuit(
    lengthMeters = lengthMeters,
    totalCorners = totalCorners
)