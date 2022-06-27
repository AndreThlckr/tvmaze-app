package io.github.andrethlckr.tvmaze.main.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun TvMazeTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = DarkColors,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
