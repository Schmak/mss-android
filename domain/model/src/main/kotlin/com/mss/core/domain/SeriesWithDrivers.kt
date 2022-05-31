package com.mss.core.domain

import com.mss.core.domain.ref.DriverReference
import com.mss.core.domain.ref.SeriesReference

data class SeriesWithDrivers(
    val series: SeriesReference,
    val drivers: List<DriverReference>,
) {
    companion object
}