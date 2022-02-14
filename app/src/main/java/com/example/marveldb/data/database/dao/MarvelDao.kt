package com.example.marveldb.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marveldb.data.model.Character

@Dao
interface MarvelDao {
    @Query("SELECT * FROM character") //" ORDER BY author DESC")
    suspend fun getAllCharacters():List<Character>


    @Query("SELECT * FROM character WHERE id = :movieId") //" ORDER BY author DESC")
    suspend fun getCharacter(movieId:Int):Character

   // @Query("UPDATE character SET favorite = :isFavorite WHERE id = :movieId") //" ORDER BY author DESC")
    //suspend fun setFavoriteCharacter(movieId:Int,isFavorite:Int):Unit

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies:List<Character>)

    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    //suspend fun insertDetail(detail: DetailEntity)

    @Query("DELETE FROM character")
    suspend fun deleteAllCharacters()
}
