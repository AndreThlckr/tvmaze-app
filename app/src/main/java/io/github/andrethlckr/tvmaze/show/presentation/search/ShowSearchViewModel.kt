package io.github.andrethlckr.tvmaze.show.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.andrethlckr.tvmaze.main.data.source.remote.retrofit.networkresult.NetworkResult
import io.github.andrethlckr.tvmaze.show.domain.Show
import io.github.andrethlckr.tvmaze.show.domain.ShowRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class ShowSearchViewModel(
    private val repository: ShowRepository
) : ViewModel() {

    private val _state = MutableStateFlow(
        ShowSearchState(
            query = "",
            shows = emptyList(),
            isLoading = false,
            loadingFailed = false
        )
    )
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            state
                .map { it.query }
                .debounce(300)
                .distinctUntilChanged()
                .collectLatest { query -> loadShows(query) }
        }
    }

    fun notifySearchChanged(query: String) {
        _state.update {
            it.copy(
                query = query,
                isLoading = true
            )
        }
    }

    private fun loadShows(query: String) {
        viewModelScope.launch {
            when (val result = repository.search(query)) {
                is NetworkResult.Success -> show(result.data)
                else -> showError()
            }
        }
    }

    private fun show(shows: List<Show>) {
        _state.update {
            it.copy(
                shows = shows,
                isLoading = false,
                loadingFailed = false
            )
        }
    }

    private fun showError() {
        _state.update {
            it.copy(
                loadingFailed = true,
                isLoading = false
            )
        }
    }
}

data class ShowSearchState(
    val query: String,
    val shows: List<Show>,
    val isLoading: Boolean,
    val loadingFailed: Boolean
)
