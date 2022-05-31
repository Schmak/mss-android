package com.mss.core.network.v3.mapper

import com.mss.core.domain.Driver
import com.mss.core.network.v3.mapper.common.LocalDateMapper
import com.mss.core.network.v3.mapper.common.ResourceLinkMapper
import com.mss.core.network.v3.mapper.ref.CountryReferenceMapper
import com.mss.core.network.v3.mapper.ref.DriverReferenceMapper
import com.mss.core.network.v3.model.DriverDto
import com.mss.core.utils.Mapper

object DriverMapper : Mapper<DriverDto, Driver> {
    override fun DriverDto.map() = Driver(
        name = name,
        slug = uuid,
        picture = picture,
        nationality = nationality?.let(CountryReferenceMapper),
        dateOfBirth = dateOfBirth?.let(LocalDateMapper),
        age = age,
        dateOfDeath = dateOfDeath?.let(LocalDateMapper),
        placeOfBirth = placeOfBirth,
        placeOfDeath = placeOfDeath,
        resourceLinks = resourceLinks.mapNotNull(ResourceLinkMapper),
        relations = relations.map {
            Driver.Relation(
                driver = it.driver.let(DriverReferenceMapper),
                relationship = it.relationship
            )
        }
    )
}