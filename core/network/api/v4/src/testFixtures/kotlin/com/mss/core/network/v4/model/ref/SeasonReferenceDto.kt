package com.mss.core.network.v4.model.ref

fun SeasonReferenceDto.Companion.create(
    name: String = "season name",
    slug: String = "season slug",
    year: Int = 1999,
    endYear: Int = 2000,
) = SeasonReferenceDto(
    name = name,
    slug = slug,
    year = year,
    endYear = endYear
)