package com.mss.core.ui.data.mock

import com.mss.core.domain.ref.CountryReference

object MockCountryData {
    val names = listOf(
        "Italy", "Belgium", "USA", "Finland", "Great Britain"
    )
    val flags = listOf(
        "nl", "de", "at", "ir", "bf", "nz", "it", "error"
    ).map {
        "https://assets.motorsportstats.com/flags/svg/$it.svg"
    }

    val countryReference = CountryReference(
        name = names.first(),
        picture = flags.first(),
    )
}