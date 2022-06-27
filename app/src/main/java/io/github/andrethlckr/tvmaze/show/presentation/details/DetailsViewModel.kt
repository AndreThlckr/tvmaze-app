package io.github.andrethlckr.tvmaze.show.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.andrethlckr.tvmaze.episode.domain.Episode
import io.github.andrethlckr.tvmaze.episode.domain.Season
import io.github.andrethlckr.tvmaze.main.data.source.remote.retrofit.networkresult.NetworkResult
import io.github.andrethlckr.tvmaze.show.domain.Genre
import io.github.andrethlckr.tvmaze.show.domain.Schedule
import io.github.andrethlckr.tvmaze.show.domain.ShowDetails
import io.github.andrethlckr.tvmaze.show.domain.ShowId
import io.github.andrethlckr.tvmaze.show.domain.ShowRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val showId: ShowId,
    private val repository: ShowRepository
) : ViewModel() {

    private val _state = MutableStateFlow<DetailsState>(DetailsState.Loading)
    val state = _state.asStateFlow()

    init {
        loadDetails()
    }

    fun notifyReload() {
        loadDetails()
    }

    private fun loadDetails() {
        viewModelScope.launch {
            when (val result = repository.getDetails(forShowId = showId)) {
                is NetworkResult.Success -> show(result.data)
                else -> showError()
            }
        }
    }

    private fun show(details: ShowDetails) {
        _state.update {
            DetailsState.Loaded(
                title = details.title,
                posterUrl = details.posterUrl,
                schedule = details.schedule,
                genres = details.genres,
                summary = details.summary,
                episodes = details.episodes
                    .sortedBy { it.season.number }
                    .groupBy { it.season }
            )
        }
    }

    private fun showError() {
        _state.update { DetailsState.Error }
    }
}

sealed class DetailsState {

    object Loading : DetailsState()

    data class Loaded(
        val title: String,
        val posterUrl: String,
        val schedule: Schedule,
        val genres: List<Genre>,
        val summary: String,
        val episodes: Map<Season, List<Episode>>
    ) : DetailsState()

    object Error : DetailsState()
}
