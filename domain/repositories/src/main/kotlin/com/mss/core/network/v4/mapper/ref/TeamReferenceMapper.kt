package com.mss.core.network.v4.mapper.ref

import com.mss.core.domain.ref.TeamReference
import com.mss.core.network.v4.model.ref.TeamReferenceDto
import com.mss.core.utils.Mapper

object TeamReferenceMapper : Mapper<TeamReferenceDto, TeamReference> {
    override fun TeamReferenceDto.map() = TeamReference(
        name = name,
        picture = picture,
        slug = slug,
    )
}