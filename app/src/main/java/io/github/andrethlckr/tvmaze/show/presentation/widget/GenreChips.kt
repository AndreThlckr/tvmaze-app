package io.github.andrethlckr.tvmaze.show.presentation.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import io.github.andrethlckr.tvmaze.main.presentation.widget.Chip
import io.github.andrethlckr.tvmaze.show.domain.Genre

@Composable
fun GenreChips(genres: List<Genre>) {
    FlowRow(
        Modifier
            .padding(top = 8.dp)
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
    ) {
        genres.forEach { genre ->
            Chip(text = genre.value)
        }
    }
}
