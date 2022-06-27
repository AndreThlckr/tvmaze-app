package io.github.andrethlckr.tvmaze.show.data.source.remote.service.factory

import io.github.andrethlckr.tvmaze.show.data.source.remote.service.MazeTvService

interface MazeTvServiceFactory {

    fun create(): MazeTvService
}
