package com.mss.core.network.v4.mapper

import com.mss.core.domain.Team
import com.mss.core.network.v4.mapper.common.ResourceLinkMapper
import com.mss.core.network.v4.mapper.ref.ConstructorReferenceMapper
import com.mss.core.network.v4.model.TeamDto
import com.mss.core.utils.Mapper

object TeamMapper : Mapper<TeamDto, Team> {
    override fun TeamDto.map() = Team(
        name = name,
        picture = logo ?: picture,
        constructors = constructors.map(ConstructorReferenceMapper),
        resourceLinks = resourceLinks.mapNotNull(ResourceLinkMapper)
    )
}