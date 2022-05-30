package com.mss.core.network.v3.model

import com.mss.core.network.v3.model.common.ResourceLinkDto
import com.mss.core.network.v3.model.ref.CountryReferenceDto
import com.mss.core.network.v3.model.ref.DriverReferenceDto

data class DriverDto(
    val uuid: String,
    val name: String,
    val picture: String?,
    val nationality: CountryReferenceDto?,
    val dateOfBirth: Long?,
    val age: Int?,
    val dateOfDeath: Long?,
    val yearOfBirth: Int?,
    val yearOfDeath: Int?,
    val countryOfBirth: CountryReferenceDto?,
    val countryOfDeath: CountryReferenceDto?,
    val placeOfBirth: String?,
    val placeOfDeath: String?,
    val hasStats: Boolean,
    val resourceLinks: List<ResourceLinkDto>,
    val relations: List<Relation>
) {
    data class Relation(
        val driver: DriverReferenceDto,
        val relationship: String
    ) {
        companion object
    }

    companion object
}