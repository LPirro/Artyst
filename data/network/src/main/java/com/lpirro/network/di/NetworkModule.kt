package com.lpirro.network.di

import com.lpirro.network.BuildConfig
import com.lpirro.network.MusicBrainzApiService
import com.lpirro.network.interceptor.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val API_BASE_URL = "https://musicbrainz.org/ws/2/"

    @Singleton
    @Provides
    fun provideMusicBrainzApiService(): MusicBrainzApiService = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideOkHttp())
        .build()
        .create(MusicBrainzApiService::class.java)

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private fun provideOkHttp(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(provideLoggingInterceptor())
        }

        okHttpClient.addInterceptor(HeaderInterceptor())

        return okHttpClient.build()
    }
}
