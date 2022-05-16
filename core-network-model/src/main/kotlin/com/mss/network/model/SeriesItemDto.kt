package com.mss.network.model

import com.mss.network.model.ref.EventReferenceDto

data class SeriesItemDto(
    val uuid: String,
    val name: String,
    val code: String,
    val picture: String?,
    val region: String,
    val category: String,
    val firstFullSeasonYear: String?,
    val lastEvent: EventReferenceDto,
    val lastEventDate: Long?
) {
    companion object
}