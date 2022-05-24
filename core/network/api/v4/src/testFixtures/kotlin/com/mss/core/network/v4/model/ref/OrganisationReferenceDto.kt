package com.mss.core.network.v4.model.ref

fun OrganisationReferenceDto.Companion.create(
    name: String = "organisation name",
    slug: String = "organisation slug",
) = OrganisationReferenceDto(
    name = name,
    slug = slug
)