package com.mss.core.network.v4.model

import com.mss.core.network.v4.model.common.ResourceLinkDto
import com.mss.core.network.v4.model.ref.ConstructorReferenceDto
import com.mss.core.network.v4.model.ref.CountryReferenceDto

data class TeamDto(
    val name: String,
    val picture: String?,
    val logo: String?,
    val headquarters: HeadQuarters?,
    val constructors: List<ConstructorReferenceDto>,
    val resourceLinks: List<ResourceLinkDto>
) {
    data class HeadQuarters(
        val address: String,
        val country: CountryReferenceDto?
    ) {
        companion object
    }

    companion object
}