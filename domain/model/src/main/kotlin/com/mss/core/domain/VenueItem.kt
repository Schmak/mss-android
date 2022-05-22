package com.mss.core.domain

import com.mss.core.domain.ref.CountryReference

data class VenueItem(
    val slug: String,
    val name: String,
    val country: CountryReference,
    val picture: String?,
) {
    companion object
}