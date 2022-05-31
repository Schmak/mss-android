package com.mss.core.network.v4.model

import com.mss.core.network.v4.model.ref.SeriesReferenceDto
import com.mss.core.network.v4.model.ref.TeamReferenceDto

data class SeriesWithTeamDto(
    val series: SeriesReferenceDto,
    val team: TeamReferenceDto?,
    val firstFullSeasonYear: String?,
    val hasStats: Boolean,
    val active: Boolean
) {
    companion object
}