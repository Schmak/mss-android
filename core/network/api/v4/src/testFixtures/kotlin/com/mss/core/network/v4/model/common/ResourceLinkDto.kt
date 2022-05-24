package com.mss.core.network.v4.model.common

fun ResourceLinkDto.Companion.create(
    name: String? = "link name",
    href: String = "link href",
    type: String = "link type",
) = ResourceLinkDto(
    name = name,
    href = href,
    type = type
)