package com.mss.core.network.v4.model.ref

fun SessionReferenceDto.Companion.create(
    name: String = "session name",
    shortName: String? = "session shortName",
    slug: String = "session-slug",
    code: String? = "session code",
) = SessionReferenceDto(
    name = name,
    shortName = shortName,
    slug = slug,
    code = code
)