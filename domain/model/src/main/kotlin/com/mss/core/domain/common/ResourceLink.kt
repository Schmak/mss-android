package com.mss.core.domain.common

sealed interface ResourceLink {
    val url: String

    data class Facebook(override val url: String) : ResourceLink {
        companion object
    }

    data class Instagram(override val url: String) : ResourceLink {
        companion object
    }

    data class Twitter(override val url: String) : ResourceLink {
        companion object
    }

    data class YouTube(override val url: String) : ResourceLink {
        companion object
    }
}