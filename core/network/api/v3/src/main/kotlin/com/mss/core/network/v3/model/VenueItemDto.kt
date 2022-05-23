package com.mss.core.network.v3.model

import com.mss.core.network.v3.model.ref.CountryReferenceDto
import com.mss.core.network.v3.model.ref.EventReferenceDto

data class VenueItemDto(
    val uuid: String,
    val name: String,
    val country: CountryReferenceDto,
    val lastEvent: EventReferenceDto?,
    val lastEventDate: Long?,
    val picture: String?,
) {
    companion object
}