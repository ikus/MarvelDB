package com.example.marveldb.data

import com.example.marveldb.data.model.CharacterDataWrapper
import com.example.marveldb.data.network.CharacterService
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val api: CharacterService
    //,
    //private val movieDao: MovieDao
){

    suspend fun getAllCharactersFromApi(): CharacterDataWrapper<Character> {
        val response = api.getAllCharacters(0,100)
        return response
    }
}