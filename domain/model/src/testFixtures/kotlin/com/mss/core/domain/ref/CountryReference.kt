package com.mss.core.domain.ref

fun CountryReference.Companion.create(
    name: String = "country name",
    picture: String? = "country picture",
) = CountryReference(
    name = name,
    picture = picture,
)