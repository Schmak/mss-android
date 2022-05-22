package com.mss.network.model

import com.mss.network.model.ref.CountryReferenceDto
import com.mss.network.model.ref.EventReferenceDto

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