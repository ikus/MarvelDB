package com.example.marveldb.data.database
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.marveldb.data.database.dao.MarvelDao
import com.example.marveldb.data.model.Character

@Database(entities = [Character::class], version = 1)
@TypeConverters(Converters::class)
abstract class MarvelDatabase: RoomDatabase() {

    abstract fun getMarvelDao(): MarvelDao
}


