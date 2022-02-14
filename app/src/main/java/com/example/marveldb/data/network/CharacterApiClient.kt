package com.example.marveldb.data.network

import com.example.marveldb.data.model.CharacterDataWrapper
import com.example.marveldb.data.model.Character
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiClient {

    @GET("v1/public/characters")
    suspend fun getCharacters(): CharacterDataWrapper<Character>

    @GET("v1/public/characters")
    suspend fun getCharacters(@Query("offset") offset: Int, @Query("limit") limit: Int): CharacterDataWrapper<Character>


    @GET("v1/public/characters/{id}")
    suspend fun getCharacter(@Path("id") id: Int): CharacterDataWrapper<Character>


}