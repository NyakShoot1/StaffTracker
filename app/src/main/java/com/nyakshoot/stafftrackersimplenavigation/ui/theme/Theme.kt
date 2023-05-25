package com.nyakshoot.stafftrackersimplenavigation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = green_main,
    primaryVariant = Purple700,
    secondary = gray_main,
    background = black_main,
    surface = text,
    onSurface = card,
    secondaryVariant = blue
)

private val LightColorPalette = lightColors(
    primary = green_main,
    primaryVariant = Purple700,
    secondary = gray_main,
    background = black_main,
    surface = text,
    onSurface = card

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun StaffTrackerSimpleNavigationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}