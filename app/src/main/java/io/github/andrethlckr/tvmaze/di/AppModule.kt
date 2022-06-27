package io.github.andrethlckr.tvmaze.di

import io.github.andrethlckr.tvmaze.episode.data.EpisodeRepositoryImpl
import io.github.andrethlckr.tvmaze.episode.domain.EpisodeId
import io.github.andrethlckr.tvmaze.episode.domain.EpisodeRepository
import io.github.andrethlckr.tvmaze.episode.presentation.EpisodeViewModel
import io.github.andrethlckr.tvmaze.show.presentation.details.DetailsViewModel
import io.github.andrethlckr.tvmaze.show.presentation.shows.ShowsViewModel
import io.github.andrethlckr.tvmaze.show.data.ShowRepositoryImpl
import io.github.andrethlckr.tvmaze.show.data.source.remote.service.MazeTvService
import io.github.andrethlckr.tvmaze.show.data.source.remote.service.factory.MazeTvServiceFactory
import io.github.andrethlckr.tvmaze.show.data.source.remote.service.factory.MazeTvServiceFactoryImpl
import io.github.andrethlckr.tvmaze.show.domain.ShowId
import io.github.andrethlckr.tvmaze.show.domain.ShowRepository
import io.github.andrethlckr.tvmaze.show.presentation.search.ShowSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    factory<MazeTvServiceFactory> { MazeTvServiceFactoryImpl() }

    single<MazeTvService> { get<MazeTvServiceFactory>().create() }

    single<ShowRepository> { ShowRepositoryImpl(get()) }

    single<EpisodeRepository> { EpisodeRepositoryImpl(get()) }

    viewModel { ShowsViewModel(get()) }

    viewModel { ShowSearchViewModel(get()) }

    viewModel { (showId: ShowId) ->
        DetailsViewModel(
            showId = showId,
            repository = get()
        )
    }

    viewModel { (episodeId: EpisodeId) ->
        EpisodeViewModel(
            episodeId = episodeId,
            repository = get()
        )
    }
}
