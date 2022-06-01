package com.mss.core.domain

import com.mss.core.domain.common.ResourceLink
import com.mss.core.domain.ref.ConstructorReference

data class Team(
    val name: String,
    val picture: String?,
    val constructors: List<ConstructorReference>,
    val resourceLinks: List<ResourceLink>
) {
    companion object
}