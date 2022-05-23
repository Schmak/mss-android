package com.mss.core.network.v4.model.ref

data class SessionReferenceDto(
    val name: String,
    val shortName: String?,
    val slug: String,
    val code: String?,
) {
    companion object
}