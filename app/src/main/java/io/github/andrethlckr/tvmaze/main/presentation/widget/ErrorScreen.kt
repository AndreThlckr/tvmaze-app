package io.github.andrethlckr.tvmaze.main.presentation.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.andrethlckr.tvmaze.main.presentation.theme.TvMazeTheme

@Composable
fun ErrorScreen(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        RetryButton(onRetry = onRetry)
    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
    TvMazeTheme {
        ErrorScreen(
            onRetry = { }
        )
    }
}
