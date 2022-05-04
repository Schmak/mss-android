package com.mss.app.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mss.app.ui.theme.shapes.SlopeSideShape
import com.mss.app.ui.theme.shapes.SlopeSideShape.CornerType
import com.mss.app.ui.theme.shapes.SlopeSideShape.CornerType.Bottom
import com.mss.app.ui.theme.shapes.SlopeSideShape.CornerType.Top

@Composable
fun SlopeSideContainer(
    color: Color,
    modifier: Modifier = Modifier,
    leftCornerWidth: Dp = 0.dp,
    rightCornerWidth: Dp = 0.dp,
    leftCornerType: CornerType = Top,
    rightCornerType: CornerType = Top,
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

@Preview()
@Composable
fun PreviewCutCornerContainer() {
    SlopeSideContainer(
        color = Color.Green,
        leftCornerWidth = 10.dp,
        leftCornerType = Bottom,
        rightCornerType = Top,
        rightCornerWidth = 20.dp
    ) {
        Text("Hello", color = Color.White)
    }
}