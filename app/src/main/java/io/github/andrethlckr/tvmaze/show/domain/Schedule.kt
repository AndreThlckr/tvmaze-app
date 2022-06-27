package io.github.andrethlckr.tvmaze.show.domain

data class Schedule(
    val days: List<Day>,
    val time: String
)

@JvmInline
value class Day(val value: String)
