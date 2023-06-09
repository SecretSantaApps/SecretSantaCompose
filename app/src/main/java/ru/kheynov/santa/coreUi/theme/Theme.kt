package ru.kheynov.santa.coreUi.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import ru.kheynov.santa.coreUi.Dimensions
import ru.kheynov.santa.coreUi.LocalSpacing

private val DarkColorPalette = darkColors(
    primary = Purple700,
    primaryVariant = Purple700,
    secondary = Teal200,
)

private val LightColorPalette = lightColors(
    primary = Purple700,
    primaryVariant = Purple700,
    secondary = Teal200,

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
fun SecretSantaTheme(
    darkTheme: Boolean = /*isSystemInDarkTheme()*/ false,
    content: @Composable ()
    -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    CompositionLocalProvider(LocalSpacing provides Dimensions()) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content,
        )
    }
}