package com.mss.core.ui.theme.shapes

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.mss.core.ui.theme.shapes.SlopeSideShape.CornerType.Bottom
import com.mss.core.ui.theme.shapes.SlopeSideShape.CornerType.Top

class SlopeSideShape(
    private val leftCornerType: CornerType = Top,
    private val rightCornerType: CornerType = Top,
    private val leftCornerWidth: Dp = 0.dp,
    private val rightCornerWidth: Dp = 0.dp,
) : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline =
        with(density) {
            Path().apply {
                moveTo(if (leftCornerType == Top) leftCornerWidth.toPx() else 0f, 0f)
                lineTo(size.width - if (rightCornerType == Top) rightCornerWidth.toPx() else 0f, 0f)
                lineTo(size.width - if (rightCornerType == Bottom) rightCornerWidth.toPx() else 0f, size.height)
                lineTo(if (leftCornerType == Bottom) leftCornerWidth.toPx() else 0f, size.height)
                close()
            }.let(Outline::Generic)
        }

    enum class CornerType {
        Top,
        Bottom,
    }
}