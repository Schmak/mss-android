package com.mss.core.ui.components.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.mss.core.ui.R
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.model.UiItem
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.theme.Dimensions.Tile
import com.mss.core.ui.theme.imageBackground
import com.mss.core.ui.theme.stubColor
import com.mss.core.ui.theme.stubHighlightColor

@Composable
fun Tile(
    item: UiItem?,
    itemConfig: UiItem.Configuration,
    modifier: Modifier = Modifier,
) {
    @Composable
    fun Modifier.placeholder() =
        this.placeholder(
            visible = item == null,
            color = MaterialTheme.colors.stubColor,
            shape = RoundedCornerShape(4.dp),
            highlight = PlaceholderHighlight.shimmer(highlightColor = MaterialTheme.colors.stubHighlightColor),
        )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.width(Tile.width)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item?.imageUrl)
                .error(R.drawable.ic_mss)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = item?.title,
            modifier = Modifier
                .size(Tile.imageSize)
                .clip(CircleShape)
                .background(MaterialTheme.colors.imageBackground)
                .placeholder(),
        )

        Text(
            text = item?.title?.uppercase().orEmpty(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .widthIn(min = Tile.titlePlaceHolderWidth)
                .padding(top = 4.dp)
                .placeholder()
        )
        if (itemConfig.hasSubtitle)
            Text(
                text = item?.subtitle.orEmpty(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.secondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .widthIn(min = Tile.subtitlePlaceHolderWidth)
                    .padding(top = 2.dp)
                    .placeholder()
            )
    }
}

@Composable
private fun PreviewTile(item: UiItem?, itemConfig: UiItem.Configuration) {
    AppTheme {
        Surface {
            Tile(item = item, itemConfig = itemConfig)
        }
    }
}

@MultiPreview
@Composable
fun PreviewLeadingSeriesTile() {
    PreviewTile(MockSeriesData.leadingSeries.first(), UiItem.Configuration.NoSubtitle)
}

@MultiPreview
@Composable
fun PreviewCategoriesSeriesTile() {
    PreviewTile(MockSeriesData.regionSeries.first(), UiItem.Configuration.Default)
}

@MultiPreview
@Composable
fun PreviewStubTile() {
    PreviewTile(null, UiItem.Configuration.NoSubtitle)
}

@MultiPreview
@Composable
fun PreviewStubTileWithSubtitle() {
    PreviewTile(null, UiItem.Configuration.Default)
}