package com.mss.network.model

import com.mss.network.model.ref.CountryReferenceDto
import com.mss.network.model.ref.EventReferenceDto
import com.mss.network.model.ref.SeriesReferenceDto
import com.mss.network.model.ref.TeamReferenceDto

data class DriverItemDto(
    val uuid: String,
    val name: String,
    val picture: String?,
    val dateOfBirth: Long?,
    val yearOfBirth: Int?,
    val lastEvent: EventReferenceDto?,
    val lastTeam: TeamReferenceDto?,
    val lastEventYear: Int?,
    val series: SeriesReferenceDto?,
    val nationalities: List<CountryReferenceDto>
) {
    companion object
}