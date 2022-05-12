package com.mss.app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mss.app.R
import com.mss.core.ui.components.SlopeSideContainer
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.theme.Blue25
import com.mss.core.ui.theme.Blue44
import com.mss.core.ui.theme.White
import com.mss.core.ui.theme.shapes.SlopeSideShape.CornerType.Bottom
import com.mss.core.ui.theme.shapes.SlopeSideShape.CornerType.Top

@Composable
fun TopBar(
    onMenuClick: () -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(color = Blue25) {
        Row(modifier = modifier.fillMaxWidth().height(48.dp)) {
                SlopeSideContainer(
                    color = MaterialTheme.colors.primary,
                    rightCornerType = Bottom,
                    rightCornerWidth = 24.dp
                ) {
                    IconButton(
                        onClick = onMenuClick,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            tint = White,
                            contentDescription = stringResource(R.string.menu)
                        )
                    }
                }
            MssLogo(modifier = Modifier.padding(start = 4.dp, bottom = 8.dp))
            Spacer(modifier = Modifier.fillMaxWidth().weight(1.0f))
            SlopeSideContainer(
                color = Blue44,
                leftCornerType = Top,
                leftCornerWidth = 24.dp,
            ) {
                IconButton(
                    onClick = onSearchClick,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        tint = White,
                        contentDescription = stringResource(R.string.search)
                    )
                }
            }
        }
    }
}

@Preview(widthDp = 400)
@Composable
fun PreviewTopBar() {
    AppTheme {
        TopBar(
            onMenuClick = {},
            onSearchClick = {},
        )
    }
}