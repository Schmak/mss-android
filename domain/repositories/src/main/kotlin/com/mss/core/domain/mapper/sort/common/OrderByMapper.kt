package com.mss.core.domain.mapper.sort.common

import com.mss.core.domain.sort.OrderBy
import com.mss.core.domain.sort.SortField
import com.mss.core.utils.Mapper
import com.mss.network.model.sort.OrderByDto
import com.mss.network.model.sort.SortFieldDto

class OrderByMapper<DOMAIN : SortField, DTO : SortFieldDto>(
    private val mapper: Mapper<DOMAIN, DTO>
) : Mapper<OrderBy<DOMAIN>, OrderByDto<DTO>> {
    override fun OrderBy<DOMAIN>.map(): OrderByDto<DTO> =
        OrderByDto(
            field = field.let(mapper),
            direction = direction.let(DirectionMapper)
        )
}