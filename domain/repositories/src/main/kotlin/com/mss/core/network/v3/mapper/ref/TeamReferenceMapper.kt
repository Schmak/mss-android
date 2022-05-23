package com.mss.core.network.v3.mapper.ref

import com.mss.core.domain.ref.TeamReference
import com.mss.core.network.v3.model.ref.TeamReferenceDto
import com.mss.core.utils.Mapper

internal object TeamReferenceMapper : Mapper<TeamReferenceDto, TeamReference> {
    override fun TeamReferenceDto.map() =
        TeamReference(
            slug = uuid,
            name = name,
            picture = picture
        )
}