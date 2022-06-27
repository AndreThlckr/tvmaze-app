package io.github.andrethlckr.tvmaze.main.presentation.widget

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.andrethlckr.tvmaze.main.presentation.theme.TvMazeTheme

@Composable
fun LoadingScreen() {
    LoadingRow(
        modifier = Modifier.fillMaxHeight()
    )
}

@Preview
@Composable
fun LoadingScreenPreview() {
    TvMazeTheme {
        LoadingScreen()
    }
}
