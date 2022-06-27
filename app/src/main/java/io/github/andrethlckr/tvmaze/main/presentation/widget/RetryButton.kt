package io.github.andrethlckr.tvmaze.main.presentation.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.andrethlckr.tvmaze.R
import io.github.andrethlckr.tvmaze.main.presentation.theme.TvMazeTheme

@Composable
fun RetryButton(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            color = MaterialTheme.colors.onBackground,
            text = stringResource(id = R.string.an_error_occurred)
        )
        Button(
            onClick = onRetry
        ) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Preview
@Composable
fun RetryButtonPreview() {
    TvMazeTheme {
        RetryButton(
            onRetry = {}
        )
    }
}
