package com.mss.core.domain

import com.mss.core.domain.ref.CountryReference
import com.mss.core.domain.ref.TeamReference

data class DriverItem(
    val slug: String,
    val name: String,
    val picture: String?,
    val lastTeam: TeamReference?,
    val nationality: CountryReference?,
) {
    companion object
}