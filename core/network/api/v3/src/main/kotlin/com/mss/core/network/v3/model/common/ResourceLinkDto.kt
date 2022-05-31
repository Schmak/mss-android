package com.mss.core.network.v3.model.common

data class ResourceLinkDto(
    val name: String?,
    val href: String,
    val type: String
) {
    companion object
}