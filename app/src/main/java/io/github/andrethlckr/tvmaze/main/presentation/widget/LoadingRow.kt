package io.github.andrethlckr.tvmaze.main.presentation.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.andrethlckr.tvmaze.main.presentation.theme.TvMazeTheme

@Composable
fun LoadingRow(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun LoadingRowPreview() {
    TvMazeTheme {
        LoadingRow()
    }
}
