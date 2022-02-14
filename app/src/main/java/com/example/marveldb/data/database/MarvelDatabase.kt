package com.example.marveldb.data.database
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.marveldb.data.database.dao.MarvelDao
import com.example.marveldb.data.model.Character
//import com.example.moviedisplay.data.database.dao.MovieDao
//import com.example.moviedisplay.data.database.entities.DetailEntity
//import com.example.moviedisplay.data.database.entities.MovieEntity


@Database(entities = [Character::class/*,DetailEntity::class*/], version = 1)
@TypeConverters(Converters::class)
abstract class MarvelDatabase: RoomDatabase() {

    abstract fun getMarvelDao(): MarvelDao
}


