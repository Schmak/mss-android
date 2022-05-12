package com.mss.network.model.ref

data class EventReferenceDto(
    val name: String,
    val uuid: String,
    val code: String?
) {
    companion object
}