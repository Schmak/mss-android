package com.mss.core.network.v3.model

import com.mss.core.network.v3.model.ref.SeasonReferenceDto
import com.mss.core.network.v3.model.ref.SeriesReferenceDto

data class SeriesInfoDto(
    val series: SeriesReferenceDto,
    val currentSeason: SeasonReferenceDto?
) {
    companion object
}