package com.example.marveldb.data

import android.database.Cursor
import com.example.marveldb.data.database.dao.MarvelDao
import com.example.marveldb.data.model.CharacterDataWrapper
import com.example.marveldb.data.model.Character
import com.example.marveldb.data.network.CharacterService
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val api: CharacterService,
    private val marvelDao: MarvelDao
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


    suspend fun getMovieFromDatabase(movieId:Int):Character{
        val response: Character = marvelDao.getCharacter(movieId)
        return  response
    }


    suspend fun setFavoriteInDatabase(movieId:Int,isFavorite:Boolean):Boolean{
        var valor = if(isFavorite ==true)  1 else 0
        //movieDao.setFavoriteMovie(movieId, valor)
        return true
    }



    suspend fun getAllCharactersFromDatabase():List<Character>{
        val response: List<Character> = marvelDao.getAllCharacters()
        return response
    }


    suspend fun getAllCharactersFromDatabase(limit:Int,offset:Int):List<Character>{
        val response: List<Character> = marvelDao.getAllCharacters(limit,offset)
        return response
    }

    /*
    suspend fun getCursorAllCharactersFromDatabase(): Cursor {
        val response: Cursor = marvelDao.getCursorAllCharacters()
        return response
    }
*/

    suspend fun insertCharacters(characters:List<Character>){
        marvelDao.insertAll(characters)
    }

    suspend fun insertDetails(character:Character){
        //movieDao.insertAll(character)
    }

    suspend fun clearCharacters(){
        marvelDao.deleteAllCharacters()
    }

    suspend fun clearDetails(){
        //movieDao.deleteAllMovies()
    }

}