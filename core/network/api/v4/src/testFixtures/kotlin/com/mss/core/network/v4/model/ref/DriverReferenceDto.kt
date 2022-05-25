package com.mss.core.network.v4.model.ref

fun DriverReferenceDto.Companion.create(
    name: String = "driver name",
    slug: String = "driver slug",
    code: String? = "driver code",
    picture: String? = "driver picture",
    countryFlag: String? = "driver countryFlag",
) = DriverReferenceDto(
    name = name,
    slug = slug,
    code = code,
    picture = picture,
    countryFlag = countryFlag
)