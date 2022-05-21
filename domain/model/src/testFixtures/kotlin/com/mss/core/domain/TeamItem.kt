package com.mss.core.domain

import com.mss.core.domain.ref.CountryReference
import com.mss.core.domain.ref.create

fun TeamItem.Companion.create(
    slug: String = "team-slug",
    name: String = "team name",
    picture: String? = "team picture",
    country: CountryReference? = CountryReference.create(),
) = TeamItem(
    slug = slug,
    name = name,
    picture = picture,
    country = country,
)