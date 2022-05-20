package com.mss.network.model.ref

fun CountryReferenceDto.Companion.create(
    name: String = "country name",
    slug: String = "country-slug",
    picture: String? = "country picture",
) = CountryReferenceDto(
    name = name,
    slug = slug,
    picture = picture
)