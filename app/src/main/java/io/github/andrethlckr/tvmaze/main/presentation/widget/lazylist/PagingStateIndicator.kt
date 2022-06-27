package io.github.andrethlckr.tvmaze.main.presentation.widget.lazylist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import io.github.andrethlckr.tvmaze.main.presentation.widget.ErrorScreen
import io.github.andrethlckr.tvmaze.main.presentation.widget.LoadingRow
import io.github.andrethlckr.tvmaze.main.presentation.widget.RetryButton

fun LazyListScope.pagingStateIndicator(
    state: CombinedLoadStates,
    onRetry: () -> Unit
) {
    state.apply {
        when {
            refresh is LoadState.Loading -> {
                item { LoadingRow(modifier = Modifier.fillParentMaxSize()) }
            }
            append is LoadState.Loading -> {
                item { LoadingRow() }
            }
            refresh is LoadState.Error -> {
                item {
                    ErrorScreen(
                        onRetry = onRetry,
                        modifier = Modifier.fillParentMaxSize()
                    )
                }
            }
            append is LoadState.Error -> {
                item {
                    RetryButton(
                        onRetry = onRetry
                    )
                }
            }
        }
    }
}
