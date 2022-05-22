package com.mss.core.domain.ref

fun TeamReference.Companion.create(
    slug: String = "Team slug",
    name: String = "Team name",
    picture: String? = "Team picture",
) = TeamReference(
    slug = slug,
    name = name,
    picture = picture,
)
