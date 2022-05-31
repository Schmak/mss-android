package com.mss.core.domain

import com.mss.core.domain.common.ResourceLink
import com.mss.core.domain.common.create
import com.mss.core.domain.ref.ConstructorReference
import com.mss.core.domain.ref.create

fun Team.Companion.create(
    name: String = "team name",
    picture: String? = "team picture",
    constructors: List<ConstructorReference> = listOf(ConstructorReference.create()),
    resourceLinks: List<ResourceLink> = listOf(ResourceLink.Facebook.create())
) = Team(
    name = name,
    picture = picture,
    constructors = constructors,
    resourceLinks = resourceLinks
)