package com.example.marveldb.ui


import android.util.Log
import com.example.marveldb.data.CharacterRepository
//import com.example.marveldb.data.database.entities.toDatabase
//import com.example.marveldb.data.model.detail.DetailModel
//import com.example.marveldb.domain.model.MovieDetail
//import com.example.marveldb.domain.model.Movie
//import com.example.marveldb.domain.model.ResultItem
//import com.example.marveldb.domain.model.toDomain
import com.example.marveldb.data.model.Character
import javax.inject.Inject
//import kotlin.NotImplementedError as NotImplementedError1("Falta implementar")

class GetDetailUseCase @Inject constructor(private val repository: CharacterRepository) {

    suspend operator fun invoke(movieID:Int): Character {
        //val search = repository.getDetailMovieFromApi(movieID)
        val detail= repository.getAllCharactersFromApi()//repository.getAllCharactersFromApi()   //.getDetailMovieFromApi(movieID)
        Log.d("DETAIIL::", detail.toString())
        //TODO: Refacorizar la pagina qu devuelve

        /*
        if(detail != null){
            Log.i("INFO::","Tenemos detalles" +detail.originalLanguage)
            repository.clearDetails()
            repository.insertDetails(detail.toDatabase())//(movies.map { it.toDatabase() })
            detail
        }else{
            //Obter de la base de datos
        }


*/
        return Character()
    }

}

