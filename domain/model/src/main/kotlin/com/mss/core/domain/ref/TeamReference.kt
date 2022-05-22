package com.mss.core.domain.ref

data class TeamReference(
    val name: String,
    val slug: String,
    val picture: String?,
) {
    companion object
}