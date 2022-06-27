package io.github.andrethlckr.tvmaze.show.data.source.remote.service.factory

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.github.andrethlckr.tvmaze.main.data.source.remote.retrofit.networkresult.NetworkResultCallAdapterFactory
import io.github.andrethlckr.tvmaze.show.data.source.remote.service.MazeTvService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MazeTvServiceFactoryImpl: MazeTvServiceFactory {

    private val moshiAdapter = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    override fun create(): MazeTvService {
        val retrofit = Retrofit.Builder()
            .baseUrl(MazeTvService.MAZE_TV_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshiAdapter))
            .addCallAdapterFactory(NetworkResultCallAdapterFactory())
            .client(okHttpClient)
            .build()

        return retrofit.create(MazeTvService::class.java)
    }
}