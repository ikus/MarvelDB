package com.example.moviedisplay.ui.adapter

import android.annotation.SuppressLint
import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marveldb.R
import com.example.marveldb.data.CharacterRepository
import com.example.marveldb.data.model.ApiImage
import com.example.marveldb.data.model.CharacterDataContainer
import com.example.marveldb.data.model.CharacterDataWrapper
import com.example.marveldb.data.model.Character
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class CharacterCursorAdapter(private val cursorDatos: Cursor?, private val onClickListener:(Character) -> Unit): RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater =  LayoutInflater.from(parent.context)
        return MovieViewHolder(layoutInflater.inflate(R.layout.item_movie,parent,false))
    }

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item:Character
        cursorDatos?.moveToPosition(position)
        var id = cursorDatos?.getInt(cursorDatos?.getColumnIndex("id"))
        var name = cursorDatos?.getString(cursorDatos?.getColumnIndex("name"))
        var urlImage =cursorDatos?.getString(cursorDatos?.getColumnIndex("thumbnail"))
        item = Character(id,name,"","","",null, ApiImage(urlImage!!,"jpg"))  //?.get(position)!! //maindata!![position]
        holder.render(item,onClickListener)
    }

    override fun getItemCount(): Int = cursorDatos?.count!!

}