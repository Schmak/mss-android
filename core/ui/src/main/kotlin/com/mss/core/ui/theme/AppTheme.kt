@file:Suppress("unused")

package com.mss.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

private val LightThemeColors = lightColors(
    primary = Red,
)

private val DarkThemeColors = darkColors(
    primary = Red,
    surface = Blue21,
    background = Blue21,
    onSurface = White,
    onBackground = White,
)

val Colors.imageBackground: Color
    get() = if (isLight) Gray95 else White

val Colors.capriSubtitle: Color
    get() = if (isLight) PrussianBlue else Capri

val Colors.cyanSubtitle: Color
    get() = if (isLight) Cyan34 else Cyan82

val Colors.divider: Color
    get() = if (isLight) Black10 else White10

val Colors.stubColor
    get() = if (isLight) Gray95 else Gray90

val Colors.stubHighlightColor
    get() = White

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