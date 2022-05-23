package com.mss.core.network.v4.model.ref

fun EventReferenceDto.Companion.create(
    name: String = "event name",
    shortName: String? = "event shortName",
    slug: String = "event-slug",
    code: String? = "event code",
    countryFlag: String? = "event countryFlag",
) = EventReferenceDto(
    name = name,
    shortName = shortName,
    slug = slug,
    code = code,
    countryFlag = countryFlag
)