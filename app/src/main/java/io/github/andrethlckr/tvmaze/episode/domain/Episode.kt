package io.github.andrethlckr.tvmaze.episode.domain

@JvmInline
value class EpisodeId(val value: Long)

@JvmInline
value class Season(val number: Int)

data class Episode(
    val id: EpisodeId,
    val name: String,
    val number: Int,
    val season: Season,
    val summary: String,
    val imageUrl: String?
)
