package com.mss.core.ui.components.info

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun BlockHeader(
    @StringRes titleId: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = titleId).uppercase(),
        style = MaterialTheme.typography.h3,
        color = MaterialTheme.colors.secondary,
        modifier = modifier
    )
}