package com.mss.core.network.v4.model

import com.mss.core.network.v4.model.common.ResourceLinkDto
import com.mss.core.network.v4.model.ref.CountryReferenceDto

data class VenueDto(
    val name: String,
    val picture: String?,
    val country: CountryReferenceDto?,
    val address: String?,
    val coordinates: Coordinates?,
    val resourceLinks: List<ResourceLinkDto>
) {
    data class Coordinates(
        val latitude: Double,
        val longitude: Double,
    ) {
        companion object
    }

    companion object
}