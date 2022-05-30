package com.mss.core.network.v3.model

import com.mss.core.network.v3.model.common.ResourceLinkDto
import com.mss.core.network.v3.model.common.create
import com.mss.core.network.v3.model.ref.CountryReferenceDto
import com.mss.core.network.v3.model.ref.DriverReferenceDto
import com.mss.core.network.v3.model.ref.create

fun DriverDto.Companion.create(
    uuid: String = "driver uuid",
    name: String = "driver name",
    picture: String? = "driver picture",
    nationality: CountryReferenceDto? = CountryReferenceDto.create(),
    dateOfBirth: Long? = 1234,
    age: Int? = 12,
    dateOfDeath: Long? = null,
    yearOfBirth: Int? = null,
    yearOfDeath: Int? = null,
    countryOfBirth: CountryReferenceDto? = CountryReferenceDto.create(),
    countryOfDeath: CountryReferenceDto? = null,
    placeOfBirth: String? = "driver placeOfBirth",
    placeOfDeath: String? = "driver placeOfDeath",
    hasStats: Boolean = true,
    resourceLinks: List<ResourceLinkDto> = listOf(ResourceLinkDto.create()),
    relations: List<DriverDto.Relation> = listOf(DriverDto.Relation.create()),
) = DriverDto(
    uuid = uuid,
    name = name,
    picture = picture,
    nationality = nationality,
    dateOfBirth = dateOfBirth,
    age = age,
    dateOfDeath = dateOfDeath,
    yearOfBirth = yearOfBirth,
    yearOfDeath = yearOfDeath,
    countryOfBirth = countryOfBirth,
    countryOfDeath = countryOfDeath,
    placeOfBirth = placeOfBirth,
    placeOfDeath = placeOfDeath,
    hasStats = hasStats,
    resourceLinks = resourceLinks,
    relations = relations
)

fun DriverDto.Relation.Companion.create(
    driver: DriverReferenceDto = DriverReferenceDto.create(),
    relationship: String = "driver relationship",
) = DriverDto.Relation(
    driver = driver,
    relationship = relationship
)