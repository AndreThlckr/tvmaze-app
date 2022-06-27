package io.github.andrethlckr.tvmaze.show.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.github.andrethlckr.tvmaze.main.presentation.theme.TvMazeTheme
import io.github.andrethlckr.tvmaze.main.presentation.widget.Image
import io.github.andrethlckr.tvmaze.show.domain.Show
import io.github.andrethlckr.tvmaze.show.domain.ShowId

@Composable
fun ShowRow(
    show: Show,
    onClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        if (show.posterUrl != null) {
            val posterModifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .height(96.dp)
                .width(64.dp)
            Image(
                url = show.posterUrl,
                contentDescription = show.title,
                loading = {
                    Box(
                        modifier = posterModifier.background(MaterialTheme.colors.surface)
                    )
                },
                modifier = posterModifier
            )
        }

        Text(
            text = show.title,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(start = 8.dp)
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun ShowRowPreview() {
    TvMazeTheme {
        ShowRow(
            show = Show(
                id = ShowId(1),
                title = "Dark",
                posterUrl = "https://static.tvmaze.com/uploads/images/medium_portrait/262/655754.jpg"
            )
        )
    }
}
