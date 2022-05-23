package com.mss.core.network.v3.model

import com.mss.core.network.v3.model.ref.SeasonReferenceDto
import com.mss.core.network.v3.model.ref.SeriesReferenceDto
import com.mss.core.network.v3.model.ref.create

fun SeriesInfoDto.Companion.create(
    series: SeriesReferenceDto = SeriesReferenceDto.create(),
    currentSeason: SeasonReferenceDto = SeasonReferenceDto.create()
) = SeriesInfoDto(
    series = series,
    currentSeason = currentSeason
)