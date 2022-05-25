package com.mss.core.network.v4.mapper

import com.mss.core.domain.VenueDetails
import com.mss.core.network.v4.mapper.ref.EventReferenceMapper
import com.mss.core.network.v4.mapper.ref.SeriesReferenceMapper
import com.mss.core.network.v4.model.VenueDetailsDto
import com.mss.core.utils.Mapper

object VenueDetailsMapper : Mapper<VenueDetailsDto, VenueDetails> {
    override fun VenueDetailsDto.map() = VenueDetails(
        firstEvent = firstEvent?.let(EventYearMapper),
        lastEvent = lastEvent?.let(EventYearMapper),
        lastCircuit = lastCircuit?.let(CircuitMapper),
        series = series.map(SeriesReferenceMapper),
    )

    object CircuitMapper : Mapper<VenueDetailsDto.Circuit, VenueDetails.Circuit> {
        override fun VenueDetailsDto.Circuit.map() = VenueDetails.Circuit(
            lengthMeters = lengthMeters,
            totalCorners = totalCorners
        )
    }

    object EventYearMapper : Mapper<VenueDetailsDto.EventYear, VenueDetails.EventYear> {
        override fun VenueDetailsDto.EventYear.map() = VenueDetails.EventYear(
            event = event.let(EventReferenceMapper),
            year = year,
        )
    }
}