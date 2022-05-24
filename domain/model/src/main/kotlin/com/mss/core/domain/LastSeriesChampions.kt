package com.mss.core.domain

import com.mss.core.domain.ref.DriverReference
import com.mss.core.domain.ref.TeamReference

data class LastSeriesChampions(
    val drivers: List<DriverReference>,
    val team: TeamReference?
) {
    companion object
}