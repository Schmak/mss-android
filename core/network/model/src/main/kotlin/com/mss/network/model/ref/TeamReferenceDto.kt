package com.mss.network.model.ref

data class TeamReferenceDto(
    val name: String,
    val uuid: String,
    val code: String?,
    val picture: String?
) {
    companion object
}