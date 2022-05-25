package com.mss.core.domain

import com.mss.core.domain.common.ResourceLink

data class Series(
    val name: String,
    val shortName: String?,
    val picture: String?,
    val firstSeason: String,
    val organisation: String?,
    val category: String,
    val links: List<ResourceLink>,
) {
    companion object
}