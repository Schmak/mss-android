package com.mss.core.domain

import com.mss.core.domain.ref.SeasonReference
import com.mss.core.domain.ref.SeriesReference

data class SeriesInfo(
    val series: SeriesReference,
    val currentSeason: SeasonReference?
) {
    companion object
}