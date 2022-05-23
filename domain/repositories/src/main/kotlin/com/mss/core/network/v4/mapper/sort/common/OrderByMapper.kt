package com.mss.core.network.v4.mapper.sort.common

import com.mss.core.domain.sort.OrderBy
import com.mss.core.domain.sort.SortField
import com.mss.core.network.v4.model.sort.OrderByDto
import com.mss.core.network.v4.model.sort.SortFieldDto
import com.mss.core.utils.Mapper

class OrderByMapper<DOMAIN : SortField, DTO : SortFieldDto>(
    private val mapper: Mapper<DOMAIN, DTO>
) : Mapper<OrderBy<DOMAIN>, OrderByDto<DTO>> {
    override fun OrderBy<DOMAIN>.map(): OrderByDto<DTO> =
        OrderByDto(
            field = field.let(mapper),
            direction = direction.let(DirectionMapper)
        )
}