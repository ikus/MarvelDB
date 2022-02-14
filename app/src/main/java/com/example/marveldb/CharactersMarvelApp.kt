package com.example.marveldb
import android.app.Application
import com.example.marveldb.data.Prefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CharactersMarvelApp:Application(){
    companion object{
        lateinit var prefs:Prefs
    }
    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
    }
}
