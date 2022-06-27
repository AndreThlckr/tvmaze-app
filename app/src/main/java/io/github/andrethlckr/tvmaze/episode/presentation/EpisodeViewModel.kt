package io.github.andrethlckr.tvmaze.episode.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.andrethlckr.tvmaze.episode.domain.Episode
import io.github.andrethlckr.tvmaze.episode.domain.EpisodeId
import io.github.andrethlckr.tvmaze.episode.domain.EpisodeRepository
import io.github.andrethlckr.tvmaze.episode.domain.Season
import io.github.andrethlckr.tvmaze.main.data.source.remote.retrofit.networkresult.NetworkResult
import io.github.andrethlckr.tvmaze.show.domain.Genre
import io.github.andrethlckr.tvmaze.show.domain.Schedule
import io.github.andrethlckr.tvmaze.show.domain.ShowDetails
import io.github.andrethlckr.tvmaze.show.domain.ShowId
import io.github.andrethlckr.tvmaze.show.domain.ShowRepository
import io.github.andrethlckr.tvmaze.show.presentation.details.DetailsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EpisodeViewModel(
    private val episodeId: EpisodeId,
    private val repository: EpisodeRepository
) : ViewModel() {

    private val _state = MutableStateFlow<EpisodeState>(EpisodeState.Loading)
    val state = _state.asStateFlow()

    init {
        loadDetails()
    }

    fun notifyReload() {
        loadDetails()
    }

    private fun loadDetails() {
        viewModelScope.launch {
            when (val result = repository.get(episodeId)) {
                is NetworkResult.Success -> show(result.data)
                else -> showError()
            }
        }
    }

    private fun show(episode: Episode) {
        _state.update {
            EpisodeState.Loaded(episode)
        }
    }

    private fun showError() {
        _state.update { EpisodeState.Error }
    }
}

sealed class EpisodeState {

    object Loading : EpisodeState()

    data class Loaded(
        val episode: Episode
    ) : EpisodeState()

    object Error : EpisodeState()
}
