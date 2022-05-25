package com.mss.core.network.v4.model

import com.mss.core.network.v4.model.ref.EventReferenceDto
import com.mss.core.network.v4.model.ref.SeriesReferenceDto

data class VenueDetailsDto(
    val firstEvent: EventYear?,
    val lastEvent: EventYear?,
    val lastCircuit: Circuit?,
    val series: List<SeriesReferenceDto>,
) {
    data class EventYear(
        val event: EventReferenceDto,
        val year: Int
    ) {
        companion object
    }

    data class Circuit(
        val type: String?,
        val lengthMeters: Double?,
        val totalCorners: Int?,
        val picture: String?,
    ) {
        companion object
    }

    companion object
}