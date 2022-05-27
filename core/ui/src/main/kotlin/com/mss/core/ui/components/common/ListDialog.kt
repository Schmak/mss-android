package com.mss.core.ui.components.common

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mss.core.ui.R
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.data.mock.MockSeriesData
import com.mss.core.ui.theme.AppTheme
import com.mss.core.ui.theme.GhostWhite

@Composable
fun ListDialog(
    @StringRes titleId: Int,
    items: List<String>,
    onDismissRequest: () -> Unit = {},
    onItemClick: (idx: Int) -> Unit = {},
) {
    AppTheme(darkTheme = false) {
        Dialog(onDismissRequest = onDismissRequest) {
            Surface {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 25.dp, vertical = 8.dp),
                    ) {
                        Text(
                            text = stringResource(titleId).uppercase(),
                            style = MaterialTheme.typography.h1,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.weight(1f),
                        )
                        IconButton(onClick = onDismissRequest) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = stringResource(R.string.close),
                            )
                        }
                    }
                    Divider()
                    LazyColumn {
                        itemsIndexed(items) { idx, item ->
                            Text(
                                text = item,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier
                                    .background(if (idx % 2 == 0) Color.Transparent else GhostWhite)
                                    .fillMaxWidth()
                                    .clickable {
                                        onItemClick(idx)
                                        onDismissRequest()
                                    }
                                    .padding(vertical = 10.dp, horizontal = 25.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@MultiPreview
@Composable
fun PreviewListDialog() {
    AppTheme {
        Surface {
            ListDialog(
                titleId = R.string.series,
                items = MockSeriesData.regions,
            )
        }
    }
}