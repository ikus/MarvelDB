package com.example.marveldb.ui

import android.util.Log
import com.example.marveldb.CharactersMarvelApp.Companion.prefs
import com.example.marveldb.data.CharacterRepository
import com.example.marveldb.data.model.CharacterDataWrapper
import javax.inject.Inject
import com.example.marveldb.data.model.Character
import com.example.marveldb.data.model.CharacterDataContainer

class GetCharactersUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend operator fun invoke(): CharacterDataWrapper<Character> {
        val dbupdated = prefs.getDBUpdated()
        if(dbupdated){
            //Obtenerr de la base de datos
            var search = CharacterDataWrapper<Character>()
            search.data = CharacterDataContainer(0,100,0,0,null)
            search.data?.results = repository.getAllCharactersFromDatabase(50,0)
            return search
        }else{
            val search = repository.getAllCharactersFromApi(0,100)
            Log.i("INFO::","Tenemos resultadas" +search.toString())
            repository.insertCharacters(search.data?.results ?: emptyList())
            return search
        }
    }
}