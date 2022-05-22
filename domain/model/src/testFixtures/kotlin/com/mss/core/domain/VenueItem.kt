package com.mss.core.domain

import com.mss.core.domain.ref.CountryReference
import com.mss.core.domain.ref.create

fun VenueItem.Companion.create(
    slug: String = "Venue slug",
    name: String = "Venue name",
    country: CountryReference = CountryReference.create(),
    picture: String? = "Venue picture",
) = VenueItem(
    slug = slug,
    name = name,
    country = country,
    picture = picture
)