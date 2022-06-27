package io.github.andrethlckr.tvmaze.episode.data.source.remote.dto

import com.squareup.moshi.Json
import io.github.andrethlckr.tvmaze.episode.domain.Episode
import io.github.andrethlckr.tvmaze.episode.domain.EpisodeId
import io.github.andrethlckr.tvmaze.episode.domain.Season
import io.github.andrethlckr.tvmaze.show.data.source.remote.dto.ImageResponse

data class EpisodeResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "number") val number: Int,
    @Json(name = "season") val season: Int,
    @Json(name = "summary") val summary: String,
    @Json(name = "image") val image: ImageResponse?
)

fun EpisodeResponse.toModel() = Episode(
    id = EpisodeId(id),
    name = name,
    number = number,
    season = Season(number = season),
    summary = summary,
    imageUrl = image?.originalImageUrl,
)
