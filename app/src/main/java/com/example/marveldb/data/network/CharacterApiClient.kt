package com.example.marveldb.data.network

import com.example.marveldb.data.model.CharacterDataWrapper
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface CharacterApiClient {


    @GET("v1/public/characters")
    suspend fun getCharacters(@Query("offset") offset: Int, @Query("limit") limit: Int): CharacterDataWrapper<Character>

/*
    @GET("movie/popular?")
    suspend fun getPopularMovie(@Query("page") page: Int): Response<SearchModel?>?

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query("page") page: Int,
        @Query("query") query: String?
    ): Call<SearchModel?>?

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(@Path("movie_id") movie_id: String?): Response<DetailModel?>?


    @GET("movie/upcoming")
    suspend fun getUpcomingMovie(): Response<UpcomingModel?>?

*/
}