package com.mss.core.network.v4.model.ref

data class EventReferenceDto(
    val name: String,
    val shortName: String?,
    val slug: String,
    val code: String?,
    val countryFlag: String?,
) {
    companion object
}