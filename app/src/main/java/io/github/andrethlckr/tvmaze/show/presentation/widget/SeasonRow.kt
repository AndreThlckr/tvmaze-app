package io.github.andrethlckr.tvmaze.show.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.andrethlckr.tvmaze.R
import io.github.andrethlckr.tvmaze.episode.domain.Season

@Composable
fun SeasonRow(season: Season) {
    Text(
        text = stringResource(id = R.string.season, season.number),
        color = MaterialTheme.colors.onBackground,
        style = MaterialTheme.typography.body1,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
            .background(color = MaterialTheme.colors.surface.copy(alpha = 0.1f))
            .padding(8.dp)
    )
}
