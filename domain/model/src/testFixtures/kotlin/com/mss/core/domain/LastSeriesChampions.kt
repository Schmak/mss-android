package com.mss.core.domain

import com.mss.core.domain.ref.DriverReference
import com.mss.core.domain.ref.TeamReference
import com.mss.core.domain.ref.create

fun LastSeriesChampions.Companion.create(
    drivers: List<DriverReference> = listOf(DriverReference.create()),
    team: TeamReference? = TeamReference.create(),
) = LastSeriesChampions(
    drivers = drivers,
    team = team
)