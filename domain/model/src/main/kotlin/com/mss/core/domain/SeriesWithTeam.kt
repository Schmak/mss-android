package com.mss.core.domain

import com.mss.core.domain.ref.SeriesReference
import com.mss.core.domain.ref.TeamReference

data class SeriesWithTeam(
    val series: SeriesReference,
    val team: TeamReference?,
) {
    companion object
}