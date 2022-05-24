package com.mss.core.network.v4.model

import com.mss.core.network.v4.model.ref.*

fun LastSeriesChampionsDto.Companion.create(
    series: SeriesReferenceDto = SeriesReferenceDto.create(),
    season: SeasonReferenceDto = SeasonReferenceDto.create(),
    drivers: List<LastSeriesChampionsDto.Driver> = emptyList(),
    team: TeamReferenceDto? = null,
) = LastSeriesChampionsDto(
    series = series,
    season = season,
    drivers = drivers,
    team = team
)

fun LastSeriesChampionsDto.Driver.Companion.create(
    driver: DriverReferenceDto = DriverReferenceDto.create(),
    team: TeamReferenceDto? = TeamReferenceDto.create()
) = LastSeriesChampionsDto.Driver(
    driver = driver,
    team = team,
)