package com.mss.network.model.ref

data class SeriesReferenceDto(
    val name: String,
    val shortName: String?,
    val uuid: String,
    val code: String?,
    val picture: String?
) {
    companion object
}