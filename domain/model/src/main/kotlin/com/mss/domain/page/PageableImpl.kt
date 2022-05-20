package com.mss.domain.page

internal data class PageableImpl(
    override val page: Int,
    override val size: Int,
) : Pageable {
    init {
        require(page >= 0) { "Page should be non-negative, but was set to '$page'" }
        require(size > 0) { "Size should be positive, but was set to '$size'" }
    }
}