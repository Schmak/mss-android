package com.mss.core.network.v4.model.ref

data class SeriesReferenceDto(
    val name: String,
    val shortName: String?,
    val code: String?,
    val slug: String,
    val uuid: String,
    val picture: String?,
) {
    companion object
}