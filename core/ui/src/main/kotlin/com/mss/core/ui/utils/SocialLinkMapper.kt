package com.mss.core.ui.utils

import com.mss.core.domain.common.ResourceLink
import com.mss.core.ui.R
import com.mss.core.ui.model.SocialLink
import com.mss.core.utils.Mapper

object SocialLinkMapper : Mapper<ResourceLink, SocialLink> {
    override fun ResourceLink.map(): SocialLink = when (this) {
        is ResourceLink.Twitter -> SocialLink(R.drawable.ic_twitter, url)
        is ResourceLink.Facebook -> SocialLink(R.drawable.ic_facebook, url)
        is ResourceLink.Instagram -> SocialLink(R.drawable.ic_instagram, url)
        is ResourceLink.YouTube -> SocialLink(R.drawable.ic_youtube, url)
    }
}