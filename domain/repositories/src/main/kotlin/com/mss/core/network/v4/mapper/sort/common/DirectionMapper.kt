package com.mss.core.network.v4.mapper.sort.common

import com.mss.core.domain.sort.Direction
import com.mss.core.network.v4.model.sort.DirectionDto
import com.mss.core.utils.Mapper

object DirectionMapper : Mapper<Direction, DirectionDto> {
    override fun Direction.map(): DirectionDto = when (this) {
        Direction.ASC -> DirectionDto.ASC
        Direction.DESC -> DirectionDto.DESC
    }
}