package io.github.andrethlckr.tvmaze.show.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import io.github.andrethlckr.tvmaze.R
import io.github.andrethlckr.tvmaze.episode.domain.Episode
import io.github.andrethlckr.tvmaze.episode.domain.EpisodeId
import io.github.andrethlckr.tvmaze.episode.domain.Season
import io.github.andrethlckr.tvmaze.episode.presentation.widget.EpisodeRow
import io.github.andrethlckr.tvmaze.main.presentation.theme.TvMazeTheme
import io.github.andrethlckr.tvmaze.main.presentation.widget.ErrorScreen
import io.github.andrethlckr.tvmaze.main.presentation.widget.Image
import io.github.andrethlckr.tvmaze.main.presentation.widget.LoadingScreen
import io.github.andrethlckr.tvmaze.show.domain.Day
import io.github.andrethlckr.tvmaze.show.domain.Genre
import io.github.andrethlckr.tvmaze.show.domain.Schedule
import io.github.andrethlckr.tvmaze.show.domain.Show
import io.github.andrethlckr.tvmaze.show.domain.ShowId
import io.github.andrethlckr.tvmaze.show.presentation.shows.ShowsFragmentDirections
import io.github.andrethlckr.tvmaze.show.presentation.widget.GenreChips
import io.github.andrethlckr.tvmaze.show.presentation.widget.ScheduleRow
import io.github.andrethlckr.tvmaze.show.presentation.widget.SeasonRow
import io.github.andrethlckr.tvmaze.show.presentation.widget.Summary
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()

    private val viewModel: DetailsViewModel by viewModel {
        parametersOf(ShowId(args.showId))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            val state by viewModel.state.collectAsState()

            TvMazeTheme {
                DetailsScreen(
                    state,
                    onReload = { viewModel.notifyReload() },
                    onEpisodeSelected = { episode -> navigateTo(episode) }
                )
            }
        }
    }

    private fun navigateTo(episode: Episode) {
        findNavController().navigate(
            DetailsFragmentDirections.goToEpisode(episode.id.value)
        )
    }
}

@Composable
fun DetailsScreen(
    state: DetailsState,
    onReload: () -> Unit,
    onEpisodeSelected: (Episode) -> Unit
) {
    when (state) {
        is DetailsState.Loading -> LoadingScreen()
        is DetailsState.Loaded -> ShowDetailsScreen(
            details = state,
            onEpisodeSelected = onEpisodeSelected
        )
        is DetailsState.Error -> ErrorScreen(onRetry = onReload)
    }
}

@Composable
fun ShowDetailsScreen(
    details: DetailsState.Loaded,
    onEpisodeSelected: (Episode) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 96.dp),
    ) {
        item {
            ShowPoster(
                posterUrl = details.posterUrl,
                modifier = Modifier
                    .fillParentMaxHeight(0.4f)
                    .fillMaxWidth()
            )
            ShowTitle(title = details.title)
            ScheduleRow(schedule = details.schedule)
            GenreChips(genres = details.genres)
            Summary(text = details.summary)
        }

        episodeList(
            episodes = details.episodes,
            onEpisodeSelected = onEpisodeSelected
        )
    }
}

@Composable
private fun ShowPoster(
    posterUrl: String,
    modifier: Modifier = Modifier
) {
    val posterModifier = modifier
        .padding(top = 8.dp)

    Image(
        url = posterUrl,
        contentDescription = stringResource(id = R.string.show_poster),
        contentScale = ContentScale.Fit,
        loading = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = posterModifier
            ) {
                CircularProgressIndicator()
            }
        },
        modifier = modifier
            .padding(top = 8.dp)
    )
}

@Composable
fun ShowTitle(title: String) {
    Text(
        text = title,
        color = MaterialTheme.colors.onBackground,
        style = MaterialTheme.typography.h1,
        modifier = Modifier.padding(start = 8.dp)
    )
}

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.episodeList(
    episodes: Map<Season, List<Episode>>,
    onEpisodeSelected: (Episode) -> Unit
) {
    item {
        Text(
            text = stringResource(id = R.string.episodes),
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h3,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(bottom = 8.dp)
        )
    }

    episodes.forEach { (season, episodes) ->
        stickyHeader { SeasonRow(season) }

        items(items = episodes) { episode ->
            EpisodeRow(
                episode = episode,
                onEpisodeSelected = { onEpisodeSelected(episode) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowDetailsPreview() {
    TvMazeTheme {
        ShowDetailsScreen(
            details = DetailsState.Loaded(
                title = "Dark",
                posterUrl = "https://static.tvmaze.com/uploads/images/medium_portrait/262/655754.jpg",
                schedule = Schedule(
                    days = listOf(Day("Monday"), Day("Thursday"), Day("Sunday")),
                    time = "22:00"
                ),
                genres = listOf(
                    Genre("Mystery"),
                    Genre("Time travel")
                ),
                summary = "A family saga with a supernatural twist.",
                episodes = mapOf(
                    Season(1) to listOf(
                        Episode(
                            id = EpisodeId(1),
                            name = "Secrets",
                            number = 1,
                            season = Season(1),
                            summary = "A local boy disappears.",
                            imageUrl = "https://static.tvmaze.com/uploads/images/medium_landscape/408/1020106.jpg"
                        )
                    )
                )
            ),
            onEpisodeSelected = { }
        )
    }
}
