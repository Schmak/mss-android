package com.mss.core.network.v4.model

import com.mss.core.network.v4.model.ref.CountryReferenceDto

data class TeamItemDto(
    val slug: String,
    val name: String,
    val picture: String?,
    val country: CountryReferenceDto?
) {
    companion object
}