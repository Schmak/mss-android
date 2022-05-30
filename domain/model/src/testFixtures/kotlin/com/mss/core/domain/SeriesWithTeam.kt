package com.mss.core.domain

import com.mss.core.domain.ref.SeriesReference
import com.mss.core.domain.ref.TeamReference
import com.mss.core.domain.ref.create

fun SeriesWithTeam.Companion.create(
    series: SeriesReference = SeriesReference.create(),
    team: TeamReference? = TeamReference.create(),
) = SeriesWithTeam(
    series = series,
    team = team,
)