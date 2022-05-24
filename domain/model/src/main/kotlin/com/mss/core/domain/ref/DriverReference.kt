package com.mss.core.domain.ref

data class DriverReference(
    val name: String,
    val slug: String,
    val picture: String?,
    val countryFlag: String?,
) {
    companion object
}