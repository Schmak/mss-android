package com.mss.network.model

import com.mss.network.model.ref.CountryReferenceDto
import com.mss.network.model.ref.create

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