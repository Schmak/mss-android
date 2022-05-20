package com.mss.network.model.sort

data class OrderByDto<T : SortFieldDto>(
    val field: T,
    val direction: DirectionDto
) {
    override fun toString() = "$field:$direction"

    companion object {
        val <T : SortFieldDto> T.asc
            get() = OrderByDto(this, DirectionDto.ASC)

        val <T : SortFieldDto> T.desc
            get() = OrderByDto(this, DirectionDto.DESC)
    }
}