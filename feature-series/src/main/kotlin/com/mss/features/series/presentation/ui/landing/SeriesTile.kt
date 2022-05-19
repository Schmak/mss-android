package com.mss.features.series.presentation.ui.landing

import android.content.res.Configuration
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.mss.core.ui.R
import com.mss.core.ui.model.UiItem
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.theme.Dimensions.Tile
import com.mss.core.ui.theme.imageBackground
import com.mss.core.ui.theme.stubColor
import com.mss.core.ui.theme.stubHighlightColor
import com.mss.features.series.data.mock.MockSeriesData

@Composable
fun SeriesTile(
    item: UiItem?,
    hasSubtitle: Boolean,
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
        if (hasSubtitle)
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
private fun PreviewTile(item: UiItem?, hasSubtitle: Boolean) {
    AppTheme {
        Surface {
            SeriesTile(item = item, hasSubtitle = hasSubtitle)
        }
    }
}

@Preview("Leading series tile")
@Preview("Leading series tile (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewLeadingSeriesTile() {
    PreviewTile(MockSeriesData.leadingSeries.first(), false)
}

@Preview("Regions series tile")
@Preview("Regions series tile (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategoriesSeriesTile() {
    PreviewTile(MockSeriesData.regionSeries.first(), true)
}

@Preview("Stub tile")
@Preview("Stub tile (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewStubTile() {
    PreviewTile(null, false)
}

@Preview("Stub tile with subtitle")
@Preview("Stub tile with subtitle (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewStubTileWithSubtitle() {
    PreviewTile(null, true)
}