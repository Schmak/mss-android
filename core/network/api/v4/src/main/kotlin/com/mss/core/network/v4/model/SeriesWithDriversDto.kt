package com.mss.core.network.v4.model

import com.mss.core.network.v4.model.ref.DriverReferenceDto
import com.mss.core.network.v4.model.ref.SeriesReferenceDto

data class SeriesWithDriversDto(
    val series: SeriesReferenceDto,
    val drivers: List<DriverReferenceDto>,
    val hasStats: Boolean,
    val active: Boolean,
) {
    companion object
}