package com.mss.core.network.v4.model

import com.mss.core.network.v4.model.ref.EventReferenceDto
import com.mss.core.network.v4.model.ref.SeriesReferenceDto
import com.mss.core.network.v4.model.ref.SessionReferenceDto

data class SessionItemDto(
    val session: SessionReferenceDto,
    val event: EventReferenceDto,
    val series: SeriesReferenceDto,
    val status: String,
    val startTime: Long?,
    val startTimeUtc: Long?,
) {
    companion object
}