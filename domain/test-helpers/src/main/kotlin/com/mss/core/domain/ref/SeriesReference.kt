package com.mss.core.domain.ref

fun SeriesReference.Companion.create(
    name: String = "Series name",
    picture: String? = "Series picture",
) = SeriesReference(
    name = name,
    picture = picture,
)
