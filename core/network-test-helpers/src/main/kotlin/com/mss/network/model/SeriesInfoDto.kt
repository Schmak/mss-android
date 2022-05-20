package com.mss.network.model

import com.mss.network.model.ref.SeasonReferenceDto
import com.mss.network.model.ref.SeriesReferenceDto
import com.mss.network.model.ref.create

fun SeriesInfoDto.Companion.create(
    series: SeriesReferenceDto = SeriesReferenceDto.create(),
    currentSeason: SeasonReferenceDto = SeasonReferenceDto.create()
) = SeriesInfoDto(
    series = series,
    currentSeason = currentSeason
)