package com.mss.core.domain

import com.mss.core.domain.ref.SeasonReference
import com.mss.core.domain.ref.SeriesReference
import com.mss.core.domain.ref.create

fun SeriesInfo.Companion.create(
    series: SeriesReference = SeriesReference.create(),
    currentSeason: SeasonReference? = SeasonReference.create(),
) = SeriesInfo(
    series = series,
    currentSeason = currentSeason
)