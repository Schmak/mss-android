package com.mss.core.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mss.core.ui.theme.shapes.SlopeSideShape

@Composable
fun SlopeSideContainer(
    color: Color,
    modifier: Modifier = Modifier,
    leftCornerWidth: Dp = 0.dp,
    rightCornerWidth: Dp = 0.dp,
    leftCornerType: SlopeSideShape.CornerType = SlopeSideShape.CornerType.Top,
    rightCornerType: SlopeSideShape.CornerType = SlopeSideShape.CornerType.Top,
    content: @Composable () -> Unit
) {
    Surface(
        color = color,
        shape = SlopeSideShape(
            leftCornerWidth = leftCornerWidth,
            leftCornerType = leftCornerType,
            rightCornerWidth = rightCornerWidth,
            rightCornerType = rightCornerType,
        ),
        modifier = modifier
    ) {
        Row {
            if (leftCornerWidth != 0.dp)
                Spacer(Modifier.width(leftCornerWidth))
            content()
            if (rightCornerWidth != 0.dp)
                Spacer(Modifier.width(rightCornerWidth))
        }
    }
}