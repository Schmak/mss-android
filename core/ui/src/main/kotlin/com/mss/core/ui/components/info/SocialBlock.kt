package com.mss.core.ui.components.info

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mss.core.ui.R
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.model.SocialLink
import com.mss.core.ui.theme.AppTheme

@Composable
fun SocialBlock(
    links: List<SocialLink>,
    modifier: Modifier = Modifier,
) {
    if (links.isNotEmpty()) {
        val uriHandler = LocalUriHandler.current
        Column(modifier = modifier) {
            Spacer(modifier = Modifier.height(20.dp))
            BlockHeader(titleId = R.string.social)
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(top = 5.dp)
            ) {
                items(links) {
                    IconButton(onClick = { uriHandler.openUri(it.url) }) {
                        Icon(
                            painter = painterResource(it.drawableId),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}

@MultiPreview
@Composable
fun PreviewSocialBlock() {
    AppTheme {
        Surface {
            SocialBlock(
                listOf(
                    SocialLink(R.drawable.ic_facebook, "url"),
                    SocialLink(R.drawable.ic_instagram, "url"),
                    SocialLink(R.drawable.ic_twitter, "url"),
                    SocialLink(R.drawable.ic_youtube, "url"),
                )
            )
        }
    }
}