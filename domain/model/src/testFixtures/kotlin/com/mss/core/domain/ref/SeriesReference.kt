package com.mss.core.domain.ref

fun SeriesReference.Companion.create(
    slug: String = "Series slug",
    name: String = "Series name",
    picture: String? = "Series picture",
) = SeriesReference(
    slug = slug,
    name = name,
    picture = picture,
)
