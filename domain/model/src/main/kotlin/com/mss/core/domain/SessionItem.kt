package com.mss.core.domain

import com.mss.core.domain.ref.EventReference
import com.mss.core.domain.ref.SeriesReference
import java.time.OffsetDateTime

data class SessionItem(
    val name: String,
    val slug: String,
    val event: EventReference,
    val series: SeriesReference,
    val startTime: OffsetDateTime?,
) {
    companion object
}