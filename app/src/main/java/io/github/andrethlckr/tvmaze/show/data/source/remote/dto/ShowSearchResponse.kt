package io.github.andrethlckr.tvmaze.show.data.source.remote.dto

import com.squareup.moshi.Json

data class ShowSearchResponse(
    @Json(name = "show") val show: ShowResponse
)
