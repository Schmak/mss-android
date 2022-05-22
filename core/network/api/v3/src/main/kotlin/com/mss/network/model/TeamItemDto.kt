package com.mss.network.model

import com.mss.network.model.ref.CountryReferenceDto

data class TeamItemDto(
    val slug: String,
    val name: String,
    val picture: String?,
    val country: CountryReferenceDto?
) {
    companion object
}