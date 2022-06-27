package io.github.andrethlckr.tvmaze.show.presentation.shows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import io.github.andrethlckr.tvmaze.show.data.source.ShowPagingSource
import io.github.andrethlckr.tvmaze.show.domain.Show
import io.github.andrethlckr.tvmaze.show.domain.ShowRepository
import kotlinx.coroutines.flow.Flow

class ShowsViewModel(
    repository: ShowRepository
) : ViewModel() {

    val shows: Flow<PagingData<Show>> = Pager(PagingConfig(pageSize = 20)) {
        ShowPagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}
