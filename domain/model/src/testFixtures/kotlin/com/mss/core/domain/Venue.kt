package com.mss.core.domain

import com.mss.core.domain.common.ResourceLink
import com.mss.core.domain.common.create
import com.mss.core.domain.ref.CountryReference
import com.mss.core.domain.ref.create

fun Venue.Companion.create(
    name: String = "venue name",
    picture: String? = "venue picture",
    country: CountryReference? = CountryReference.create(),
    address: String? = "venue address",
    resourceLinks: List<ResourceLink> = listOf(ResourceLink.Facebook.create())
) = Venue(
    name = name,
    picture = picture,
    country = country,
    address = address,
    resourceLinks = resourceLinks
)