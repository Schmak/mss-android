package com.mss.core.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mss.core.ui.R
import com.mss.core.ui.annotation.MultiPreview
import com.mss.core.ui.theme.AppTheme

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
            Icon(
                painter = painterResource(R.drawable.ic_dropdown_list_icon),
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
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
        color = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground,
        modifier = modifier,
    )
}

@Composable
private fun PreviewDropdownList(expanded: Boolean) {
    AppTheme {
        Surface {
            DropdownList(
                title = "Select region",
                items = listOf("Worldwide", "Europe", "Asia"),
                onItemSelected = { },
                selectedIdx = 0,
                initiallyExpanded = expanded,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@MultiPreview
@Composable
fun PreviewCollapsedDropdownList() {
    PreviewDropdownList(expanded = true)
}

@MultiPreview
@Composable
fun PreviewExpandedDropdownList() {
    PreviewDropdownList(expanded = false)
}