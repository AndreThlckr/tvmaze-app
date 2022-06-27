package io.github.andrethlckr.tvmaze.show.data.source.remote.dto

import com.squareup.moshi.Json

data class ShowResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "image") val image: ImageResponse?,
)
