package io.github.andrethlckr.tvmaze.episode.domain

import io.github.andrethlckr.tvmaze.main.data.source.remote.retrofit.networkresult.NetworkResult

interface EpisodeRepository {

    suspend fun get(episodeId: EpisodeId): NetworkResult<Episode>
}
