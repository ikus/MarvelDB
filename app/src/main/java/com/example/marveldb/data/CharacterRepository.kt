package com.example.marveldb.data

import com.example.marveldb.data.model.CharacterDataWrapper
import com.example.marveldb.data.model.Character
import com.example.marveldb.data.network.CharacterService
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val api: CharacterService
    //,
    //private val movieDao: MovieDao
){

    suspend fun getAllCharactersFromApi(): CharacterDataWrapper<Character> {
        val response = api.getAllCharacters()
        return response
    }


    suspend fun getAllCharactersFromApi(offset: Int,limit: Int): CharacterDataWrapper<Character> {
        val response = api.getAllCharacters(offset,limit)
        return response
    }


    suspend fun geCharacterFromApi(characterID: Int): CharacterDataWrapper<Character> {
        val response = api.getCharacter(characterID)
        return response
    }


}