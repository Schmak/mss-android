package com.mss.core.domain

import com.mss.core.domain.common.ResourceLink
import com.mss.core.domain.common.create
import com.mss.core.domain.ref.CountryReference
import com.mss.core.domain.ref.DriverReference
import com.mss.core.domain.ref.create
import com.mss.core.test.utils.DEFAULT_LOCAL_DATE
import java.time.LocalDate

fun Driver.Companion.create(
    name: String = "driver name",
    slug: String = "driver slug",
    picture: String? = "driver picture",
    nationality: CountryReference? = CountryReference.create(),
    dateOfBirth: LocalDate? = DEFAULT_LOCAL_DATE,
    age: Int? = 56,
    dateOfDeath: LocalDate? = null,
    placeOfBirth: String? = "driver placeOfBirth",
    placeOfDeath: String? = "driver placeOfDeath",
    resourceLinks: List<ResourceLink> = listOf(ResourceLink.Facebook.create()),
    relations: List<Driver.Relation> = listOf(Driver.Relation.create())
) = Driver(
    name = name,
    slug = slug,
    picture = picture,
    nationality = nationality,
    dateOfBirth = dateOfBirth,
    age = age,
    dateOfDeath = dateOfDeath,
    placeOfBirth = placeOfBirth,
    placeOfDeath = placeOfDeath,
    resourceLinks = resourceLinks,
    relations = relations
)

fun Driver.Relation.Companion.create(
    driver: DriverReference = DriverReference.create(),
    relationship: String = "brother"
) = Driver.Relation(
    driver = driver,
    relationship = relationship
)