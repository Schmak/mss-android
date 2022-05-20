package com.mss.core.domain.mapper.sort.common

import com.mss.core.domain.sort.Direction
import com.mss.core.domain.sort.Direction.ASC
import com.mss.core.domain.sort.Direction.DESC
import com.mss.core.utils.Mapper
import com.mss.network.model.sort.DirectionDto

object DirectionMapper : Mapper<Direction, DirectionDto> {
    override fun Direction.map(): DirectionDto = when (this) {
        ASC -> DirectionDto.ASC
        DESC -> DirectionDto.DESC
    }
}