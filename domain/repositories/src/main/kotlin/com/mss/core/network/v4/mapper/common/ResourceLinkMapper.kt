package com.mss.core.network.v4.mapper.common

import com.mss.core.domain.common.ResourceLink
import com.mss.core.network.v4.model.common.ResourceLinkDto
import com.mss.core.utils.Mapper

object ResourceLinkMapper : Mapper<ResourceLinkDto, ResourceLink?> {
    private const val FACEBOOK = "facebook"
    private const val TWITTER = "twitter"
    private const val INSTAGRAM = "instagram"
    private const val YOUTUBE = "youtube"

    override fun ResourceLinkDto.map(): ResourceLink? = when (type.lowercase()) {
        TWITTER -> ResourceLink.Twitter(href)
        FACEBOOK -> ResourceLink.Facebook(href)
        INSTAGRAM -> ResourceLink.Instagram(href)
        YOUTUBE -> ResourceLink.YouTube(href)
        else -> null
    }
}