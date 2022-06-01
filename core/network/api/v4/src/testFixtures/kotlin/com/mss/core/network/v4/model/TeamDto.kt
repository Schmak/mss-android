package com.mss.core.network.v4.model

import com.mss.core.network.v4.model.common.ResourceLinkDto
import com.mss.core.network.v4.model.common.create
import com.mss.core.network.v4.model.ref.ConstructorReferenceDto
import com.mss.core.network.v4.model.ref.CountryReferenceDto
import com.mss.core.network.v4.model.ref.create

fun TeamDto.Companion.create(
    name: String = "team name",
    picture: String? = "team picture",
    logo: String? = "team logo",
    headquarters: TeamDto.HeadQuarters? = TeamDto.HeadQuarters.create(),
    constructors: List<ConstructorReferenceDto> = listOf(ConstructorReferenceDto.create()),
    resourceLinks: List<ResourceLinkDto> = listOf(ResourceLinkDto.create())
) = TeamDto(
    name = name,
    picture = picture,
    logo = logo,
    headquarters = headquarters,
    constructors = constructors,
    resourceLinks = resourceLinks
)

fun TeamDto.HeadQuarters.Companion.create(
    address: String = "address",
    country: CountryReferenceDto? = CountryReferenceDto.create()
) = TeamDto.HeadQuarters(
    address = address,
    country = country
)