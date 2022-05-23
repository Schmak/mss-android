package com.mss.core.network.v4.model

import com.mss.core.network.v4.model.ref.CountryReferenceDto
import com.mss.core.network.v4.model.ref.create

fun TeamItemDto.Companion.create(
    slug: String = "team-slug",
    name: String = "team name",
    picture: String? = "team picture",
    country: CountryReferenceDto? = CountryReferenceDto.create()
) = TeamItemDto(
    slug = slug,
    name = name,
    picture = picture,
    country = country
)