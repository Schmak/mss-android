package com.mss.core.domain.sort

data class OrderBy<T : SortField>(
    val field: T,
    val direction: Direction
) {
    override fun toString() = "$field:$direction"

    companion object {
        val <T : SortField> T.asc
            get() = OrderBy(this, Direction.ASC)

        val <T : SortField> T.desc
            get() = OrderBy(this, Direction.DESC)
    }
}