package com.mss.network.model.ref

data class CountryReferenceDto(
    val name: String,
    val slug: String,
    val picture: String?,
) {
    companion object
}