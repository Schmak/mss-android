package com.mss.core.domain

import java.time.LocalDate

data class SeriesItem(
    val name: String,
    val picture: String?,
    val region: String?,
    val lastEvent: LastEvent?,
) {
    data class LastEvent(
        val name: String,
        val date: LocalDate,
    ) {
        companion object
    }

    companion object
}
