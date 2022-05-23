package com.mss.core.network.v3.model.ref

fun CountryReferenceDto.Companion.create(
    name: String = "country name",
    uuid: String = "country uuid",
    code: String = "country code",
    picture: String? = "country picture",
) = CountryReferenceDto(
    name = name,
    uuid = uuid,
    code = code,
    picture = picture,
)