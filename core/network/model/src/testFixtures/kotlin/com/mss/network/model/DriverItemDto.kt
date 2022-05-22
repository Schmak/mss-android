package com.mss.network.model

import com.mss.network.model.ref.*

fun DriverItemDto.Companion.create(
    uuid: String = "Driver uuid",
    name: String = "Driver name",
    picture: String? = "Driver picture",
    dateOfBirth: Long? = 1234,
    yearOfBirth: Int? = 2000,
    lastEvent: EventReferenceDto? = EventReferenceDto.create(),
    lastTeam: TeamReferenceDto? = TeamReferenceDto.create(),
    lastEventYear: Int? = 2020,
    series: SeriesReferenceDto? = SeriesReferenceDto.create(),
    nationalities: List<CountryReferenceDto> = listOf(CountryReferenceDto.create()),
) = DriverItemDto(
    uuid = uuid,
    name = name,
    picture = picture,
    dateOfBirth = dateOfBirth,
    yearOfBirth = yearOfBirth,
    lastEvent = lastEvent,
    lastTeam = lastTeam,
    lastEventYear = lastEventYear,
    series = series,
    nationalities = nationalities
)