package com.mss.core.network.v4.model

import com.mss.core.network.v4.model.ref.DriverReferenceDto
import com.mss.core.network.v4.model.ref.SeasonReferenceDto
import com.mss.core.network.v4.model.ref.SeriesReferenceDto
import com.mss.core.network.v4.model.ref.TeamReferenceDto

data class LastSeriesChampionsDto(
    val series: SeriesReferenceDto,
    val season: SeasonReferenceDto,
    val drivers: List<Driver>,
    val team: TeamReferenceDto?
) {
    data class Driver(
        val driver: DriverReferenceDto,
        val team: TeamReferenceDto?
    ) {
        companion object
    }

    companion object
}