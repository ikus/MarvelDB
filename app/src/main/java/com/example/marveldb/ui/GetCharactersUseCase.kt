package com.example.marveldb.ui


import android.util.Log
import com.example.marveldb.data.CharacterRepository
import com.example.marveldb.data.model.CharacterDataWrapper
import javax.inject.Inject
import com.example.marveldb.data.model.Character

class GetCharactersUseCase @Inject constructor(private val repository: CharacterRepository) {

    suspend operator fun invoke(): CharacterDataWrapper<Character> {
        val search = repository.getAllCharactersFromApi()

        //TODO: Refacorizar la pagina qu devuelve

        /*
        if(search.totalPages>0){
            Log.i("INFO::","Tenemos resultadas" +search.totalResults)
            //repository.clearMovies()
            repository.insertMovies(search.results?.map { it.toDatabase() } ?: emptyList())
            search.results
            return   search.results?.map { it.toDomain() }
        }else{
            //Obter de la base de datos
            return repository.getAllMoviesFromDatabase()
        }
*/
        return  search
    }

}