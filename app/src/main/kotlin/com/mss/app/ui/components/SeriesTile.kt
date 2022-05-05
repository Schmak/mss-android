package com.mss.app.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.mss.app.R
import com.mss.app.data.mock.MockSeriesData
import com.mss.app.model.SeriesItem
import com.mss.app.ui.theme.AppTheme
import com.mss.app.ui.theme.Dimensions.Tile
import com.mss.app.ui.theme.imageBackground

@Composable
fun SeriesTile(
    item: SeriesItem,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.width(Tile.width)
    ) {
        Box(
            modifier = Modifier.clip(CircleShape),
        ) {
            Surface(color = MaterialTheme.colors.imageBackground) {
                Image(
                    painter = rememberImagePainter(
                        data = item.imageUrl,
                        builder = {
                            error(R.drawable.ic_launcher_foreground)
                        }
                    ),
                    contentScale = ContentScale.Crop,
                    contentDescription = item.title,
                    modifier = Modifier.size(Tile.imageSize),
                )
            }
        }

        Text(
            text = item.title.uppercase(),
            style = MaterialTheme.typography.subtitle1,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp)
        )
        if (item.subtitle != null)
            Text(
                text = item.subtitle,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.secondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
    }
}

@Preview("Leading series tile")
@Preview("Leading series tile (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewLeadingSeriesTile() {
    AppTheme {
        Surface {
            SeriesTile(item = MockSeriesData.leadingSeries.first())
        }
    }
}

@Preview("Regions series tile")
@Preview("Regions series tile (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCategoriesSeriesTile() {
    AppTheme {
        Surface {
            SeriesTile(item = MockSeriesData.regionSeries.first())
        }
    }
}