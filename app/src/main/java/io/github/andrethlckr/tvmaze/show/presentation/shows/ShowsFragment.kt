package io.github.andrethlckr.tvmaze.show.presentation.shows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import io.github.andrethlckr.tvmaze.R
import io.github.andrethlckr.tvmaze.main.presentation.theme.TvMazeTheme
import io.github.andrethlckr.tvmaze.main.presentation.widget.lazylist.pagingStateIndicator
import io.github.andrethlckr.tvmaze.show.domain.Show
import io.github.andrethlckr.tvmaze.show.presentation.details.DetailsFragmentDirections
import io.github.andrethlckr.tvmaze.show.presentation.widget.ShowRow
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShowsFragment : Fragment() {

    private val viewModel: ShowsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            TvMazeTheme {
                ShowList(
                    shows = viewModel.shows,
                    onShowSelected = { show -> navigateTo(show) }
                )
            }
        }
    }

    private fun navigateTo(show: Show) {
        findNavController().navigate(
            ShowsFragmentDirections.goToDetails(show.id.value)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                navigateToSearch()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun navigateToSearch() {
        findNavController().navigate(
            ShowsFragmentDirections.goToSearch()
        )
    }
}

@Composable
fun ShowList(
    shows: Flow<PagingData<Show>>,
    onShowSelected: (Show) -> Unit
) {
    val lazyItems: LazyPagingItems<Show> = shows.collectAsLazyPagingItems()

    LazyColumn {
        items(
            items = lazyItems,
            key = { it.id.value }
        ) { show ->
            if (show != null) ShowRow(
                show = show,
                onClick = { onShowSelected(show) }
            )
        }

        pagingStateIndicator(
            state = lazyItems.loadState,
            onRetry = { lazyItems.retry() }
        )
    }
}
