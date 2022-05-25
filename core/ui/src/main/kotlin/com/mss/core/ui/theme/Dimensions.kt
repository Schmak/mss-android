package com.mss.core.ui.theme

import androidx.compose.ui.unit.dp

@Suppress("MagicNumber")
object Dimensions {
    object Screen {
        val horizontalPadding = 20.dp
    }

    object Tile {
        val width = 160.dp
        val imageSize = 120.dp
        val titlePlaceHolderWidth = imageSize * .7f
        val subtitlePlaceHolderWidth = imageSize * .6f
    }

    object InfoHeader {
        val imageSize = 140.dp
        val imageBorder = 5.dp
        val iconSize = 20.dp
    }
}