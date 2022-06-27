package io.github.andrethlckr.tvmaze.show.data.source.remote.dto

import com.squareup.moshi.Json

data class ImageResponse(
    @Json(name = "medium") val mediumImageUrl: String,
    @Json(name = "original") val originalImageUrl: String
)
