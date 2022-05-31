package com.mss.core.ui.components.info

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mss.core.ui.R
import com.mss.core.ui.theme.Dimensions
import com.mss.core.ui.theme.imageBackground
import com.mss.core.ui.theme.imageBorder

@Composable
fun InfoBlockImage(
    url: String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .error(R.drawable.ic_mss)
            .build(),
        contentScale = ContentScale.Crop,
        alignment = Alignment.TopCenter,
        contentDescription = null,
        modifier = modifier
            .size(Dimensions.InfoHeader.imageSize)
            .clip(CircleShape)
            .background(MaterialTheme.colors.imageBackground)
            .border(
                width = Dimensions.InfoHeader.imageBorder,
                color = MaterialTheme.colors.imageBorder,
                shape = CircleShape
            )
    )
}