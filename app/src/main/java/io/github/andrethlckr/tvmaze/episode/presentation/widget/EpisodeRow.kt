package io.github.andrethlckr.tvmaze.episode.presentation.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.andrethlckr.tvmaze.episode.domain.Episode

@Composable
fun EpisodeRow(
    episode: Episode,
    onEpisodeSelected: (Episode) -> Unit
) {
    Text(
        text = "${episode.number} - ${episode.name}",
        color = MaterialTheme.colors.onBackground,
        style = MaterialTheme.typography.body1,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onEpisodeSelected(episode) }
            .padding(
                start = 24.dp,
                top = 8.dp,
                bottom = 8.dp,
                end = 8.dp
            )
    )
}
