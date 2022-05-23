package com.mss.core.network.v3.model

import com.mss.core.network.v3.model.ref.CountryReferenceDto
import com.mss.core.network.v3.model.ref.EventReferenceDto
import com.mss.core.network.v3.model.ref.SeriesReferenceDto
import com.mss.core.network.v3.model.ref.TeamReferenceDto

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