package com.mss.core.network.v4.model.ref

data class CountryReferenceDto(
    val name: String,
    val slug: String,
    val picture: String?,
) {
    companion object
}