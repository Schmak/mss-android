package com.mss.core.domain

import com.mss.core.domain.ref.DriverReference
import com.mss.core.domain.ref.SeriesReference
import com.mss.core.domain.ref.create

fun SeriesWithDrivers.Companion.create(
    series: SeriesReference = SeriesReference.create(),
    drivers: List<DriverReference> = listOf(DriverReference.create()),
) = SeriesWithDrivers(
    series = series,
    drivers = drivers
)