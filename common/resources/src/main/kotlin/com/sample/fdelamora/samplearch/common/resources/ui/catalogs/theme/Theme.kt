package com.sample.fdelamora.samplearch.common.resources.ui.catalogs.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.sample.fdelamora.samplearch.core.entities.AppTheme
import com.sample.fdelamora.samplearch.core.entities.AppTheme.DARK
import com.sample.fdelamora.samplearch.core.entities.AppTheme.LIGHT
import com.sample.fdelamora.samplearch.core.entities.AppTheme.RED
import com.sample.fdelamora.samplearch.core.entities.AppTheme.SYSTEM

val LightColorPalette = lightColors(
    primary = Color.Black,
    primaryVariant = Color.Black,
    secondary = Gray700,
    secondaryVariant = Gray100,
    background = Gray200,
    surface = Gray200,

    onPrimary = Gray200,
    onSecondary = Gray200,
    onBackground = Color.Black,
    onSurface = Color.Black
)

val RedColorPalette = lightColors(
    primary = Red900,
    primaryVariant = Red900,
    secondary = Color.Black,
    secondaryVariant = Gray100,
    background = Gray200,
    surface = Gray200,

    onPrimary = Gray200,
    onSecondary = Gray200,
    onBackground = Color.Black,
    onSurface = Color.Black
)

val DarkColorPalette = darkColors(
    primary = Color.Black,
    primaryVariant = Color.Black,
    secondary = Gray200,
    secondaryVariant = Gray200,
    background = Gray900,
    surface = Gray900,

    onPrimary = Gray200,
    onSecondary = Gray900,
    onBackground = Gray200,
    onSurface = Gray200
)

@Composable
fun SampleArchitectureTheme(
    theme: AppTheme = SYSTEM,
    content: @Composable () -> Unit
) {
    val colors = when (theme) {
        SYSTEM -> if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette
        LIGHT -> LightColorPalette
        RED -> RedColorPalette
        DARK -> DarkColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
