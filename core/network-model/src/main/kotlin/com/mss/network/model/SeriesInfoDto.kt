package com.mss.network.model

import com.mss.network.model.ref.SeasonReferenceDto
import com.mss.network.model.ref.SeriesReferenceDto

data class SeriesInfoDto(
    val series: SeriesReferenceDto,
    val currentSeason: SeasonReferenceDto?
) {
    companion object
}