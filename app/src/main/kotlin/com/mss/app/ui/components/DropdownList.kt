package com.mss.app.ui.components

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mss.app.R
import com.mss.app.data.mock.MockSeriesData
import com.mss.app.ui.theme.AppTheme

@Composable
fun DropdownList(
    title: String,
    items: List<String>,
    selectedIdx: Int,
    onItemSelected: (idx: Int) -> Unit,
    modifier: Modifier = Modifier,
    initiallyExpanded: Boolean = false,
) {
    var expanded by rememberSaveable { mutableStateOf(initiallyExpanded) }
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { expanded = !expanded }
        ) {
            Item(
                text = if (expanded) title else items[selectedIdx],
                selected = expanded,
                modifier = Modifier.weight(1.0f)
            )
            @Suppress("MagicNumber")
            val angle: Float by animateFloatAsState(if (expanded) 180f else 0f)
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_dropdown_list_icon),
                contentDescription = null,
                modifier = Modifier.rotate(angle)
            )
        }

        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Column {
                Spacer(Modifier.height(8.dp))
                items.forEachIndexed { idx, text ->
                    Item(
                        text = text,
                        selected = idx == selectedIdx,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onItemSelected(idx)
                                expanded = false
                            }
                            .padding(bottom = 5.dp, start = 20.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun Item(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        maxLines = 1,
        lineHeight = 21.sp,
        overflow = TextOverflow.Ellipsis,
        color = if (selected) Color.Red else MaterialTheme.colors.onBackground,
        modifier = modifier,
    )
}

@Preview("Collapsed list", widthDp = 150)
@Preview("Collapsed list (dark)", widthDp = 150, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCollapsedDropdownList() {
    AppTheme {
        Surface {
            DropdownList(
                title = "Select region",
                items = MockSeriesData.regions,
                onItemSelected = { },
                selectedIdx = 0,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Preview("Expanded list", widthDp = 150)
@Preview("Expanded list (dark)", widthDp = 150, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewExpandedDropdownList() {
    AppTheme {
        Surface {
            DropdownList(
                title = "Select region",
                items = MockSeriesData.regions,
                selectedIdx = 2,
                initiallyExpanded = true,
                onItemSelected = { },
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}