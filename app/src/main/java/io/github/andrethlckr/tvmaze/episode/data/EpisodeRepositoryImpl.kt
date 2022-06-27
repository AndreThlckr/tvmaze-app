package io.github.andrethlckr.tvmaze.episode.data

import io.github.andrethlckr.tvmaze.episode.data.source.remote.dto.EpisodeResponse
import io.github.andrethlckr.tvmaze.episode.data.source.remote.dto.toModel
import io.github.andrethlckr.tvmaze.episode.domain.Episode
import io.github.andrethlckr.tvmaze.episode.domain.EpisodeId
import io.github.andrethlckr.tvmaze.episode.domain.EpisodeRepository
import io.github.andrethlckr.tvmaze.episode.domain.Season
import io.github.andrethlckr.tvmaze.main.data.source.remote.retrofit.networkresult.NetworkResult
import io.github.andrethlckr.tvmaze.main.data.source.remote.retrofit.networkresult.map
import io.github.andrethlckr.tvmaze.main.data.source.remote.retrofit.networkresult.mapList
import io.github.andrethlckr.tvmaze.show.data.source.remote.dto.ScheduleResponse
import io.github.andrethlckr.tvmaze.show.data.source.remote.dto.ShowDetailsResponse
import io.github.andrethlckr.tvmaze.show.data.source.remote.dto.ShowResponse
import io.github.andrethlckr.tvmaze.show.data.source.remote.service.MazeTvService
import io.github.andrethlckr.tvmaze.show.domain.Day
import io.github.andrethlckr.tvmaze.show.domain.Genre
import io.github.andrethlckr.tvmaze.show.domain.Schedule
import io.github.andrethlckr.tvmaze.show.domain.Show
import io.github.andrethlckr.tvmaze.show.domain.ShowDetails
import io.github.andrethlckr.tvmaze.show.domain.ShowId
import io.github.andrethlckr.tvmaze.show.domain.ShowRepository

/**
 * Repository for retrieving remote information from episodes on TVMaze.
 *
 * This implementation does not have a cache, so calling multiple methods might create new network
 * calls everytime.
 */
class EpisodeRepositoryImpl(
    private val service: MazeTvService
) : EpisodeRepository {

    /**
     * Returns the episode with the given [episodeId].
     */
    override suspend fun get(episodeId: EpisodeId): NetworkResult<Episode> = service
        .fetchShowEpisodes(episodeId.value)
        .map(EpisodeResponse::toModel)
}
