package com.mss.core.domain

import com.mss.core.domain.common.ResourceLink
import com.mss.core.domain.common.create

fun Series.Companion.create(
    name: String = "series name",
    shortName: String? = "series shortName",
    picture: String? = "series picture",
    firstSeason: String = "series firstSeason",
    organisation: String? = "series organisation",
    category: String = "series category",
    links: List<ResourceLink> = listOf(ResourceLink.Facebook.create()),
) = Series(
    name = name,
    shortName = shortName,
    picture = picture,
    firstSeason = firstSeason,
    organisation = organisation,
    category = category,
    links = links
)