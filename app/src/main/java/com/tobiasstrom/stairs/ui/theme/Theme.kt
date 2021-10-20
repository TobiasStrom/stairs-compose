package com.tobiasstrom.stairs.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightThemeColors = lightColors(
    primary = Primary,
    primaryVariant = PrimaryDark,
    onPrimary = Color.White,
    secondary = Secondary,
    secondaryVariant = SecondaryDark,
    onSecondary = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
    surface = Surface,
    onSurface = Color.Black,
    error = Error,
    onError = Color.White
)

val DarkThemeColors = darkColors(
    primary = Primary,
    primaryVariant = PrimaryDark,
    onPrimary = DarkOnSurface,
    secondary = Secondary,
    secondaryVariant = SecondaryDark,
    onSecondary = Color.Black,
    background = Color.Black,
    onBackground = DarkOnBackground,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    error = Error,
    onError = Color.Black
)

@Composable
fun ShortcutTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkThemeColors
    } else {
        LightThemeColors
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
