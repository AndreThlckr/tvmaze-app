package io.github.andrethlckr.tvmaze.show.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.andrethlckr.tvmaze.main.data.source.remote.retrofit.networkresult.NetworkResult
import io.github.andrethlckr.tvmaze.show.domain.Show
import io.github.andrethlckr.tvmaze.show.domain.ShowRepository
import java.io.IOException

class ShowPagingSource(
    private val repository: ShowRepository
) : PagingSource<Int, Show>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Show> {
        val pageToLoad = params.key ?: 0
        val previousPage = if (pageToLoad == 0) null else pageToLoad - 1

        return when(val result = repository.getFrom(pageToLoad)) {
            is NetworkResult.Success -> LoadResult.Page(
                data = result.data,
                prevKey = previousPage,
                nextKey = pageToLoad + 1
            )
            is NetworkResult.NotFoundError -> LoadResult.Page(
                data = emptyList(),
                prevKey = previousPage,
                nextKey = null
            )
            is NetworkResult.Failure -> LoadResult.Error(
                IOException("ShowPagingSource loading failed.")
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Show>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPageIndex = state.pages.indexOf(state.closestPageToPosition(position))

            state.pages.getOrNull(anchorPageIndex + 1)?.prevKey
                ?: state.pages.getOrNull(anchorPageIndex - 1)?.nextKey
        }
    }
}
