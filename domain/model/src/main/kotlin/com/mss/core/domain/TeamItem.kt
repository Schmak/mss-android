package com.mss.core.domain

import com.mss.core.domain.ref.CountryReference

data class TeamItem(
    val slug: String,
    val name: String,
    val picture: String?,
    val country: CountryReference?,
) {

    companion object
}