package io.github.andrethlckr.tvmaze.show.data.source.remote.dto

import com.squareup.moshi.Json

data class ScheduleResponse(
    @Json(name = "time") val time: String,
    @Json(name = "days") val days: List<String>
)
