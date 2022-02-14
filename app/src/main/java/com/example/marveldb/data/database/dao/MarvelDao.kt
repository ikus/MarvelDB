package com.example.marveldb.data.database.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marveldb.data.model.Character

@Dao
interface MarvelDao {
    @Query("SELECT * FROM character")
    suspend fun getAllCharacters():List<Character>

    @Query("SELECT * FROM character LIMIT :limit OFFSET :offset")
    suspend fun getAllCharacters(limit:Int,offset:Int):List<Character>


    @Query("SELECT * FROM character WHERE id = :movieId")
    suspend fun getCharacter(movieId:Int):Character

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies:List<Character>)

    @Query("DELETE FROM character")
    suspend fun deleteAllCharacters()
}
