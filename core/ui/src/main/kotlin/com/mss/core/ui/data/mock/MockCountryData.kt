package com.mss.core.ui.data.mock

object MockCountryData {
    val countries = listOf(
        "Italy", "Belgium", "USA", "Finland", "Great Britain"
    )
    val flags = listOf(
        "nl", "de", "at", "ir", "bf", "nz", "it", "error"
    ).map {
        "https://assets.motorsportstats.com/flags/svg/$it.svg"
    }
}