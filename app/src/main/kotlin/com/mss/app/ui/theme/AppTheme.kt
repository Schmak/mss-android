package com.mss.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

private val LightThemeColors = lightColors(
    primary = Red,
    secondary = Cyan34,
)

private val DarkThemeColors = darkColors(
    primary = Red,
    secondary = Cyan82,
    surface = Blue21,
    background = Blue21,
    onSurface = Color.White,
    onBackground = Color.White,
)

val Colors.imageBackground: Color
    get() = if (isLight) Gray95 else Color.White

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