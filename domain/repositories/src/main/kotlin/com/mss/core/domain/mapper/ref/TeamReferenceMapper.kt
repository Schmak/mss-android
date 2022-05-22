package com.mss.core.domain.mapper.ref

import com.mss.core.domain.ref.TeamReference
import com.mss.core.utils.Mapper
import com.mss.network.model.ref.TeamReferenceDto

internal object TeamReferenceMapper : Mapper<TeamReferenceDto, TeamReference> {
    override fun TeamReferenceDto.map() =
        TeamReference(
            slug = uuid,
            name = name,
            picture = picture
        )
}