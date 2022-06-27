package io.github.andrethlckr.tvmaze.main.presentation.theme

import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color

val TvMazeGreen = Color(0xFF3C948B)
val TvMazeDarkGreen = Color(0xFF093E38)

val LightGray = Color(0xFFEBEBEB)
val Gray = Color(0xFF4D4D4D)
val DarkGray = Color(0xFF212121)

val DarkColors = darkColors(
    primary = TvMazeGreen,
    primaryVariant = TvMazeDarkGreen,
    secondary = Gray,
    secondaryVariant = LightGray,
    background = DarkGray,
    surface = Gray,
    error = Color.Red,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.White
)
