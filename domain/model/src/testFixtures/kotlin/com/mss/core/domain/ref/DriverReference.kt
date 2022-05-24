package com.mss.core.domain.ref

fun DriverReference.Companion.create(
    name: String = "driver name",
    slug: String = "driver slug",
    picture: String? = "driver picture",
    countryFlag: String? = "country flag",
) = DriverReference(
    name = name,
    slug = slug,
    picture = picture,
    countryFlag = countryFlag,
)