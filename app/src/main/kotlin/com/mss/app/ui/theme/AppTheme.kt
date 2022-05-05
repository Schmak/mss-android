package com.mss.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

private val LightThemeColors = lightColors(
    primary = Red,
)

private val DarkThemeColors = darkColors(
    primary = Red,
    surface = Blue21,
    onBackground = Color.White,
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = AppTypography,
        content = {
            ProvideTextStyle(
                value = TextStyle(color = MaterialTheme.colors.onBackground),
                content = content
            )
        }
    )
}