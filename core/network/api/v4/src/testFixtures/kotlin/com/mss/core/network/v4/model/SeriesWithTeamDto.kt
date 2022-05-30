package com.mss.core.network.v4.model

import com.mss.core.network.v4.model.ref.SeriesReferenceDto
import com.mss.core.network.v4.model.ref.TeamReferenceDto
import com.mss.core.network.v4.model.ref.create

fun SeriesWithTeamDto.Companion.create(
    series: SeriesReferenceDto = SeriesReferenceDto.create(),
    team: TeamReferenceDto? = TeamReferenceDto.create(),
    firstFullSeasonYear: String? = null,
    hasStats: Boolean = true,
    active: Boolean = true,
) = SeriesWithTeamDto(
    series = series,
    team = team,
    firstFullSeasonYear = firstFullSeasonYear,
    hasStats = hasStats,
    active = active
)