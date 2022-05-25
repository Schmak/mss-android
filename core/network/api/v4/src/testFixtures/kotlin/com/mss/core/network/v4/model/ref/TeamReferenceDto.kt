package com.mss.core.network.v4.model.ref

fun TeamReferenceDto.Companion.create(
    name: String = "team name",
    slug: String = "team slug",
    code: String? = "team code",
    picture: String? = "team picture",
    countryFlag: String? = "team countryFlag",
) = TeamReferenceDto(
    name = name,
    slug = slug,
    code = code,
    picture = picture,
    countryFlag = countryFlag
)