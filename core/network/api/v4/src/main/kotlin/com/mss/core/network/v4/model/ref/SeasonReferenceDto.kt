package com.mss.core.network.v4.model.ref

data class SeasonReferenceDto(
    val name: String,
    val slug: String,
    val year: Int,
    val endYear: Int,
) {
    companion object
}