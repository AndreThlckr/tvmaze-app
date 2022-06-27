package io.github.andrethlckr.tvmaze.episode.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import io.github.andrethlckr.tvmaze.R
import io.github.andrethlckr.tvmaze.episode.domain.Episode
import io.github.andrethlckr.tvmaze.episode.domain.EpisodeId
import io.github.andrethlckr.tvmaze.episode.domain.Season
import io.github.andrethlckr.tvmaze.main.presentation.theme.TvMazeTheme
import io.github.andrethlckr.tvmaze.main.presentation.widget.ErrorScreen
import io.github.andrethlckr.tvmaze.main.presentation.widget.Image
import io.github.andrethlckr.tvmaze.main.presentation.widget.LoadingScreen
import io.github.andrethlckr.tvmaze.show.presentation.widget.Summary
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EpisodeFragment : Fragment() {

    private val args: EpisodeFragmentArgs by navArgs()

    private val viewModel: EpisodeViewModel by viewModel {
        parametersOf(EpisodeId(args.episodeId))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            val state by viewModel.state.collectAsState()

            TvMazeTheme {
                EpisodeScreen(
                    state,
                    onReload = { viewModel.notifyReload() }
                )
            }
        }
    }
}

@Composable
fun EpisodeScreen(
    state: EpisodeState,
    onReload: () -> Unit
) {
    when (state) {
        is EpisodeState.Loading -> LoadingScreen()
        is EpisodeState.Loaded -> EpisodeInformation(episode = state.episode)
        is EpisodeState.Error -> ErrorScreen(onRetry = onReload)
    }
}

@Composable
fun EpisodeInformation(episode: Episode) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(bottom = 96.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            EpisodeImage(imageUrl = episode.imageUrl)
            EpisodeTitle(title = episode.name)
            EpisodeSubtitle(
                episodeNumber = episode.number,
                season = episode.season
            )
            Summary(
                text = episode.summary,
                modifier = Modifier.padding(top = 32.dp)
            )
        }
    }
}

@Composable
private fun LazyItemScope.EpisodeImage(
    imageUrl: String?
) {
    if (imageUrl != null) {
        val imageModifier = Modifier.fillMaxWidth()

        Image(
            url = imageUrl,
            contentDescription = stringResource(id = R.string.show_poster),
            contentScale = ContentScale.Fit,
            loading = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = imageModifier.fillParentMaxHeight(0.3f)
                ) {
                    CircularProgressIndicator()
                }
            },
            modifier = imageModifier
        )
    }
}

@Composable
private fun EpisodeTitle(title: String) {
    Text(
        text = title,
        color = MaterialTheme.colors.onBackground,
        style = MaterialTheme.typography.h1,
        modifier = Modifier.padding(top = 8.dp)
    )
}

@Composable
private fun EpisodeSubtitle(
    episodeNumber: Int,
    season: Season
) {
    Text(
        text = stringResource(id = R.string.episode, episodeNumber),
        color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f),
        style = MaterialTheme.typography.body1,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(top = 8.dp)
    )
    Text(
        text = stringResource(id = R.string.season, season.number),
        color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f),
        style = MaterialTheme.typography.body2,
        fontStyle = FontStyle.Italic,
        modifier = Modifier.padding(top = 4.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun EpisodeInformationPreview() {
    TvMazeTheme {
        EpisodeInformation(
            episode = Episode(
                id = EpisodeId(1),
                name = "Secrets",
                number = 1,
                season = Season(1),
                summary = "A local boy disappears.",
                imageUrl = "https://static.tvmaze.com/uploads/images/medium_landscape/408/1020106.jpg"
            )
        )
    }
}
