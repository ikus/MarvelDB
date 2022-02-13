package com.example.marveldb.data.network

import com.example.marveldb.data.model.CharacterDataWrapper
import com.example.marveldb.data.model.Character
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterService @Inject constructor(private val api:CharacterApiClient) {
    suspend fun getAllCharacters(offset: Int,limit: Int): CharacterDataWrapper<Character>{
       return withContext(Dispatchers.IO) {
            val response = api.getCharacters(/*offset,limit*/)
            Log.e("ERROR",response.toString())
            Log.e("ERROR",response.toString())
            response
        }
    }



}