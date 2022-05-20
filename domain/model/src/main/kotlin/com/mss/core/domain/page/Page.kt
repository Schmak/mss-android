package com.mss.core.domain.page

data class Page<T>(
    val content: List<T>,
    val totalElements: Long,
    val totalPages: Int,
    val pageNumber: Int,
) {
    companion object {
        fun <T> List<T>.getPage(pageable: Pageable): Page<T> {
            val from = (pageable.page * pageable.size).coerceAtMost(this.size)
            val to = (from + pageable.size).coerceAtMost(this.size)
            return Page(
                content = subList(from, to),
                totalElements = this.size.toLong(),
                pageNumber = pageable.page,
                totalPages = (this.size + pageable.size - 1) / pageable.size,
            )
        }

        fun <T, R> Page<T>.map(transform: (T) -> R): Page<R> = Page(
            content = content.map(transform),
            totalElements = totalElements,
            totalPages = totalPages,
            pageNumber = pageNumber,
        )
    }
}