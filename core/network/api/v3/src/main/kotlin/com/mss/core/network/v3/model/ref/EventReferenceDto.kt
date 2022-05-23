package com.mss.core.network.v3.model.ref

data class EventReferenceDto(
    val name: String,
    val uuid: String,
    val code: String?
) {
    companion object
}