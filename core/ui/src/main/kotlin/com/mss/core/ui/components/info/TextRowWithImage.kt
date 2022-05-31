package com.mss.core.ui.components.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mss.core.ui.R
import com.mss.core.ui.theme.Dimensions
import com.mss.core.ui.theme.imageBackground

@Composable
fun TextRowWithImage(
    text: String,
    imageUrl: String?,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .error(R.drawable.ic_mss)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .size(Dimensions.InfoHeader.iconSize)
                .clip(CircleShape)
                .background(MaterialTheme.colors.imageBackground)
        )
        TextRow(
            text = text,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}