package io.github.andrethlckr.tvmaze.show.domain

@JvmInline
value class ShowId(val value: Long)

data class Show(
    val id: ShowId,
    val title: String,
    val posterUrl: String?
)
