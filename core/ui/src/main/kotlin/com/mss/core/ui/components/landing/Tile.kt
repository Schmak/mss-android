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
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.mss.core.ui.data.mock.MockDriverData
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.model.UiItem
import com.mss.core.ui.model.UiItem.Configuration.SubtitleColor.Capri
import com.mss.core.ui.model.UiItem.Configuration.SubtitleColor.Cyan
import com.mss.core.ui.theme.*
import com.mss.core.ui.theme.Dimensions.Tile

@Composable
fun Tile(
    item: UiItem?,
    itemConfig: UiItem.Configuration,
    modifier: Modifier = Modifier,
) {
    val placeholderVisible = item == null
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
            alignment = Alignment.TopCenter,
            contentDescription = item?.title,
            modifier = Modifier
                .size(Tile.imageSize)
                .clip(CircleShape)
                .background(MaterialTheme.colors.imageBackground)
                .placeholder(visible = placeholderVisible),
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
                .placeholder(visible = placeholderVisible)
        )
        itemConfig.subtitles.forEachIndexed { idx, config ->
            Subtitle(
                text = item?.subtitles?.getOrNull(idx),
                color = when (config) {
                    Capri -> MaterialTheme.colors.capriSubtitle
                    Cyan -> MaterialTheme.colors.cyanSubtitle
                },
                placeholderVisible = placeholderVisible,
            )
        }
    }
}

@Composable
private fun Subtitle(
    text: String?,
    color: Color,
    placeholderVisible: Boolean,
) {
    Text(
        text = text.orEmpty(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.body1,
        color = color,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .widthIn(min = Tile.subtitlePlaceHolderWidth)
            .padding(top = 2.dp)
            .placeholder(visible = placeholderVisible)
    )
}

private fun Modifier.placeholder(visible: Boolean) =
    composed {
        this.placeholder(
            visible = visible,
            color = MaterialTheme.colors.stubColor,
            shape = RoundedCornerShape(4.dp),
            highlight = PlaceholderHighlight.shimmer(highlightColor = MaterialTheme.colors.stubHighlightColor),
        )
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
fun PreviewDriverTile() {
    PreviewTile(MockDriverData.champions.first(), UiItem.Configuration.WithTwoSubtitles)
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

@MultiPreview
@Composable
fun PreviewStubWithTwoSubtitles() {
    PreviewTile(null, UiItem.Configuration.WithTwoSubtitles)
}