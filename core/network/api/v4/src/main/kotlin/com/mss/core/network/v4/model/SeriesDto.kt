package com.mss.core.network.v4.model

import com.mss.core.network.v4.model.common.ResourceLinkDto
import com.mss.core.network.v4.model.common.TemplateDto
import com.mss.core.network.v4.model.ref.OrganisationReferenceDto

data class SeriesDto(
    val name: String,
    val shortName: String?,
    val shortCode: String,
    val uuid: String,
    val slug: String,
    val picture: String?,
    val status: String,
    val firstSeason: String,
    val firstFullSeasonYear: String?,
    val organisation: OrganisationReferenceDto?,
    val headquarters: Headquarters?,
    val region: String,
    val template: TemplateDto,
    val resourceLinks: List<ResourceLinkDto>,
) {
    data class Headquarters(
        val address: String
    ) {
        companion object
    }

    companion object
}