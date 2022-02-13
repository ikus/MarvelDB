package com.example.marveldb.data.network

import com.example.marveldb.data.model.CharacterDataWrapper
import com.example.marveldb.data.model.Character
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
//import io.reactivex.Single

interface CharacterApiClient {

    @GET("v1/public/characters")
    suspend fun getCharacters(): CharacterDataWrapper<Character>

    @GET("v1/public/characters")
    suspend fun getCharacters(@Query("offset") offset: Int, @Query("limit") limit: Int): CharacterDataWrapper<Character>

    //@GET("v1/public/characters")
    //fun getCharactersSync(@Query("offset") offset: Int, @Query("limit") limit: Int, @Query("nameStartsWith") nameStartsWith: String?): CharacterDataWrapper<Character>

    //@GET("v1/public/characters")
    //fun getCharactersAsync(@Query("offset") offset: Int, @Query("limit") limit: Int, @Query("nameStartsWith") nameStartsWith: String?): CharacterDataWrapper<Character>


    @GET("v1/public/characters/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterDataWrapper<Character>


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