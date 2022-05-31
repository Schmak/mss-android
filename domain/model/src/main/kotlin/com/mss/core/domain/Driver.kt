package com.mss.core.domain

import com.mss.core.domain.common.ResourceLink
import com.mss.core.domain.ref.CountryReference
import com.mss.core.domain.ref.DriverReference
import java.time.LocalDate

data class Driver(
    val name: String,
    val slug: String,
    val picture: String?,
    val nationality: CountryReference?,
    val dateOfBirth: LocalDate?,
    val age: Int?,
    val dateOfDeath: LocalDate?,
    val placeOfBirth: String?,
    val placeOfDeath: String?,
    val resourceLinks: List<ResourceLink>,
    val relations: List<Relation>
) {
    data class Relation(
        val driver: DriverReference,
        val relationship: String
    ) {
        companion object
    }

    companion object
}