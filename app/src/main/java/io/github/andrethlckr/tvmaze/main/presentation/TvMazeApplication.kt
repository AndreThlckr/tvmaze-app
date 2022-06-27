package io.github.andrethlckr.tvmaze.main.presentation

import android.app.Application
import io.github.andrethlckr.tvmaze.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TvMazeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@TvMazeApplication)
            modules(appModule)
        }
    }
}
