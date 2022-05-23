package com.mss.core.network.v3.model.ref

data class CountryReferenceDto(
    val name: String,
    val uuid: String,
    val code: String?,
    val picture: String?,
) {
    companion object
}