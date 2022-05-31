package com.mss.core.network.v3.model.common

fun ResourceLinkDto.Companion.create(
    name: String? = "driver name",
    href: String = "driver href",
    type: String = "driver type",
) = ResourceLinkDto(
    name = name,
    href = href,
    type = type
)