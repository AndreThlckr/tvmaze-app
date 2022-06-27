package io.github.andrethlckr.tvmaze.show.data

import io.github.andrethlckr.tvmaze.episode.data.source.remote.dto.EpisodeResponse
import io.github.andrethlckr.tvmaze.episode.data.source.remote.dto.toModel
import io.github.andrethlckr.tvmaze.episode.domain.Episode
import io.github.andrethlckr.tvmaze.episode.domain.EpisodeId
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
 * Repository for retrieving remote information from shows on TVMaze.
 *
 * This implementation does not have a cache, so calling multiple methods might create new network
 * calls everytime.
 */
class ShowRepositoryImpl(
    private val service: MazeTvService
) : ShowRepository {

    /**
     * Returns shows from given [page] index.
     */
    override suspend fun getFrom(page: Int): NetworkResult<List<Show>> = service
        .fetchShowsFrom(page = page)
        .mapList(ShowResponse::toModel)

    /**
     * Search for shows that match the giver [query].
     */
    override suspend fun search(query: String): NetworkResult<List<Show>> = service
        .searchShows(query)
        .mapList { it.show.toModel() }

    /**
     * Returns the details of a show and it's episodes with the given [forShowId].
     */
    override suspend fun getDetails(forShowId: ShowId): NetworkResult<ShowDetails> = service
        .fetchShowDetails(forShowId.value)
        .map(ShowDetailsResponse::toModel)
}

private fun ShowResponse.toModel() = Show(
    id = ShowId(id),
    title = name,
    posterUrl = image?.mediumImageUrl
)

private fun ShowDetailsResponse.toModel() = ShowDetails(
    id = ShowId(id),
    title = name,
    posterUrl = image.originalImageUrl,
    schedule = schedule.toModel(),
    genres = genres.map { Genre(it) },
    summary = summary,
    episodes = embeddedEpisodes.episodes.map(EpisodeResponse::toModel)
)

private fun ScheduleResponse.toModel() = Schedule(
    days = days.map { Day(it) },
    time = time
)
