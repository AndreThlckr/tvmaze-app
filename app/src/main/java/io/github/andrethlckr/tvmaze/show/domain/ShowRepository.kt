package io.github.andrethlckr.tvmaze.show.domain

import io.github.andrethlckr.tvmaze.main.data.source.remote.retrofit.networkresult.NetworkResult

interface ShowRepository {

    suspend fun getFrom(page: Int): NetworkResult<List<Show>>

    suspend fun search(query: String): NetworkResult<List<Show>>

    suspend fun getDetails(forShowId: ShowId): NetworkResult<ShowDetails>
}
