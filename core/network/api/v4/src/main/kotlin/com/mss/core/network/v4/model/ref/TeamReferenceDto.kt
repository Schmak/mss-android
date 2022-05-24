package com.mss.core.network.v4.model.ref

data class TeamReferenceDto(
    val name: String,
    val slug: String,
    val code: String?,
    val picture: String?,
    val countryFlag: String?,
) {
    companion object
}