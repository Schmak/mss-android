package com.mss.core.ui.model

import androidx.annotation.DrawableRes

data class SocialLink(
    @DrawableRes val drawableId: Int,
    val url: String,
)
