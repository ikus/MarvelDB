package com.example.marveldb.di

import com.example.marveldb.BuildConfig
import com.example.marveldb.core.AuthInterceptor
import com.example.marveldb.data.network.CharacterApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
/*
            .addInterceptor { chain ->
                var original: Request = chain.request()
                val httpUrl: HttpUrl = original.url()
                    .newBuilder()
                    .build()
                original = original.newBuilder()
                    .url(httpUrl)
                    .build()
                chain.proceed(original)
            }
*/
            .build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieApiClient(retrofit: Retrofit): CharacterApiClient {
        return retrofit.create(CharacterApiClient::class.java)
    }
}