package com.mss.core.network.v4.model

import com.mss.core.network.v4.model.ref.DriverReferenceDto
import com.mss.core.network.v4.model.ref.SeriesReferenceDto
import com.mss.core.network.v4.model.ref.create

fun SeriesWithDriversDto.Companion.create(
    series: SeriesReferenceDto = SeriesReferenceDto.create(),
    drivers: List<DriverReferenceDto> = listOf(DriverReferenceDto.create()),
    hasStats: Boolean = true,
    active: Boolean = true,
) = SeriesWithDriversDto(
    series = series,
    drivers = drivers,
    hasStats = hasStats,
    active = active
)