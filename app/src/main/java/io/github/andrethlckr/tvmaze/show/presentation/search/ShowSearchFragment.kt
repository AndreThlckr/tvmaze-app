package io.github.andrethlckr.tvmaze.show.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.github.andrethlckr.tvmaze.R
import io.github.andrethlckr.tvmaze.main.presentation.theme.TvMazeTheme
import io.github.andrethlckr.tvmaze.show.domain.Show
import io.github.andrethlckr.tvmaze.show.domain.ShowId
import io.github.andrethlckr.tvmaze.show.presentation.widget.ShowRow
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShowSearchFragment : Fragment() {

    private val viewModel: ShowSearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            TvMazeTheme {
                val state by viewModel.state.collectAsState()

                ShowSearchList(
                    query = state.query,
                    shows = state.shows,
                    isLoading = state.isLoading,
                    loadingFailed = state.loadingFailed,
                    onQueryChanged = { query -> viewModel.notifySearchChanged(query) },
                    onShowSelected = { show -> navigateTo(show) }
                )
            }
        }
    }

    private fun navigateTo(show: Show) {
        findNavController().navigate(
            ShowSearchFragmentDirections.goToDetails(show.id.value)
        )
    }
}

@Composable
fun ShowSearchList(
    query: String,
    shows: List<Show>,
    isLoading: Boolean,
    loadingFailed: Boolean,
    onQueryChanged: (String) -> Unit,
    onShowSelected: (Show) -> Unit
) {
    Column {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChanged,
            label = { Text(text = stringResource(id = R.string.search_shows)) },
            trailingIcon = if (isLoading) {
                { CircularProgressIndicator() }
            } else null,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.onBackground
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp)
        )

        AnimatedVisibility(visible = loadingFailed) {
            ScreenMessage(
                message = stringResource(R.string.an_error_occurred)
            )
        }

        AnimatedVisibility(visible = !loadingFailed) {
            LazyColumn {
                items(
                    items = shows,
                    key = { it.id.value }
                ) { show ->
                    ShowRow(
                        show = show,
                        onClick = { onShowSelected(show) }
                    )
                }

                item {
                    if (shows.isEmpty()) {
                        ScreenMessage(
                            message = stringResource(R.string.no_shows_found)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenMessage(
    message: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 48.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            color = MaterialTheme.colors.onBackground,
            text = message
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShowSearchListPreview() {
    TvMazeTheme {
        ShowSearchList(
            query = "Dar",
            shows = listOf(
                Show(
                    id = ShowId(1),
                    title = "Dark",
                    posterUrl = "https://static.tvmaze.com/uploads/images/medium_portrait/262/655754.jpg"
                )
            ),
            isLoading = false,
            loadingFailed = false,
            onQueryChanged = { },
            onShowSelected = { },
        )
    }
}
