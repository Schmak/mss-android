package com.mss.core.domain.common

fun ResourceLink.Facebook.Companion.create(
    url: String = "facebook url"
) = ResourceLink.Facebook(
    url = url
)

fun ResourceLink.Instagram.Companion.create(
    url: String = "instagram url"
) = ResourceLink.Instagram(
    url = url
)

fun ResourceLink.Twitter.Companion.create(
    url: String = "twitter url"
) = ResourceLink.Twitter(
    url = url
)

fun ResourceLink.YouTube.Companion.create(
    url: String = "YouTube url"
) = ResourceLink.YouTube(
    url = url
)
   