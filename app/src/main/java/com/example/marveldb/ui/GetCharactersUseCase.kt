package com.example.marveldb.ui


import android.util.Log
import com.example.marveldb.data.CharacterRepository
import com.example.marveldb.data.model.CharacterDataWrapper
import javax.inject.Inject
import com.example.marveldb.data.model.Character

class GetCharactersUseCase @Inject constructor(private val repository: CharacterRepository) {

    suspend operator fun invoke(): CharacterDataWrapper<Character> {
        val search = repository.getAllCharactersFromApi(0,100)

        //TODO: Refacorizar la pagina qu devuelve
        if(search!=null){
            Log.i("INFO::","Tenemos resultadas" +search.toString())

            repository.insertCharacters(search.data?.results ?: emptyList())

            return search//.results?.map { it.toDomain() }
        }else{
            //Obter de la base de datos
            search.data?.results = repository.getAllCharactersFromDatabase()
            return search
        }
        return  search
    }

}