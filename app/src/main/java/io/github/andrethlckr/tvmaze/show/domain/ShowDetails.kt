package io.github.andrethlckr.tvmaze.show.domain

import io.github.andrethlckr.tvmaze.episode.domain.Episode

data class ShowDetails(
    val id: ShowId,
    val title: String,
    val posterUrl: String,
    val schedule: Schedule,
    val genres: List<Genre>,
    val summary: String,
    val episodes: List<Episode>
)
