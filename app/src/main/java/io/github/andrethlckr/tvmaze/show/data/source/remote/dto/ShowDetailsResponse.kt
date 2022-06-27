package io.github.andrethlckr.tvmaze.show.data.source.remote.dto

import com.squareup.moshi.Json
import io.github.andrethlckr.tvmaze.episode.data.source.remote.dto.EpisodeResponse

data class ShowDetailsResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "image") val image: ImageResponse,
    @Json(name = "genres") val genres: List<String>,
    @Json(name = "summary") val summary: String,
    @Json(name = "schedule") val schedule: ScheduleResponse,
    @Json(name = "_embedded") val embeddedEpisodes: EmbeddedEpisodes
)

data class EmbeddedEpisodes(
    @Json(name = "episodes") val episodes: List<EpisodeResponse>,
)
