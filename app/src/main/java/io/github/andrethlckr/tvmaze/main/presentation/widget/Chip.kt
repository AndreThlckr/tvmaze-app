package io.github.andrethlckr.tvmaze.main.presentation.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.andrethlckr.tvmaze.main.presentation.theme.TvMazeTheme

@Composable
fun Chip(
    text: String
) {
    Surface(
        modifier = Modifier.padding(all = 4.dp),
        shape = CircleShape,
        color = MaterialTheme.colors.primary
    ) {
        Row {
            Text(
                text = text,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun ChipPreview() {
    TvMazeTheme {
        Chip(text = "Cool text")
    }
}
