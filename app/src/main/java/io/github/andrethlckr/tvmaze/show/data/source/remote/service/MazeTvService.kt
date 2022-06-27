package io.github.andrethlckr.tvmaze.show.data.source.remote.service

import io.github.andrethlckr.tvmaze.episode.data.source.remote.dto.EpisodeResponse
import io.github.andrethlckr.tvmaze.main.data.source.remote.retrofit.networkresult.NetworkResult
import io.github.andrethlckr.tvmaze.show.data.source.remote.dto.ShowDetailsResponse
import io.github.andrethlckr.tvmaze.show.data.source.remote.dto.ShowResponse
import io.github.andrethlckr.tvmaze.show.data.source.remote.dto.ShowSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MazeTvService {

    @GET("/shows")
    suspend fun fetchShowsFrom(
        @Query("page") page: Int
    ): NetworkResult<List<ShowResponse>>

    @GET("/search/shows")
    suspend fun searchShows(
        @Query("q") query: String
    ): NetworkResult<List<ShowSearchResponse>>

    @GET("/shows/{showId}?embed=episodes")
    suspend fun fetchShowDetails(
        @Path("showId") showId: Long
    ): NetworkResult<ShowDetailsResponse>

    @GET("/episodes/{episodeId}")
    suspend fun fetchShowEpisodes(
        @Path("episodeId") episodeId: Long
    ): NetworkResult<EpisodeResponse>

    companion object {

        const val MAZE_TV_BASE_URL = "https://api.tvmaze.com"
    }
}