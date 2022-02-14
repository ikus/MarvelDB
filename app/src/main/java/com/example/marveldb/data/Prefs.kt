package com.example.marveldb.data

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    val PREFS_NAME = "com.example.marveldb.data.sharedpreferences"
    val IS_DB_UPDATED = "isdbupdated"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    fun getDBUpdated():Boolean{
        return prefs.getBoolean(IS_DB_UPDATED, false)
    }

    fun saveDBUpdated(value:Boolean){
        prefs.edit().putBoolean(IS_DB_UPDATED, value).apply()
    }

}