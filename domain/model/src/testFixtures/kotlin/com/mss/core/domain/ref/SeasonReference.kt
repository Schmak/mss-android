package com.mss.core.domain.ref

fun SeasonReference.Companion.create(
    slug: String = "Season slug",
    name: String = "Season name",
) = SeasonReference(
    slug = slug,
    name = name,
)