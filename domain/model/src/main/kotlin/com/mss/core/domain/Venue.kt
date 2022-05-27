package com.mss.core.domain

import com.mss.core.domain.common.ResourceLink
import com.mss.core.domain.ref.CountryReference

data class Venue(
    val name: String,
    val picture: String?,
    val country: CountryReference?,
    val address: String?,
    val resourceLinks: List<ResourceLink>
) {
    companion object
}