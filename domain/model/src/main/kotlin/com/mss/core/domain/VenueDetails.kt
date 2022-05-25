package com.mss.core.domain

import com.mss.core.domain.ref.EventReference
import com.mss.core.domain.ref.SeriesReference

data class VenueDetails(
    val firstEvent: EventYear?,
    val lastEvent: EventYear?,
    val lastCircuit: Circuit?,
    val series: List<SeriesReference>,
) {
    data class EventYear(
        val event: EventReference,
        val year: Int
    ) {
        companion object
    }

    data class Circuit(
        val lengthMeters: Double?,
        val totalCorners: Int?,
    ) {
        companion object
    }

    companion object
}