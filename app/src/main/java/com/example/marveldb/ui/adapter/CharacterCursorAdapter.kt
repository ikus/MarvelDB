package com.example.moviedisplay.ui.adapter

import android.annotation.SuppressLint
import android.database.Cursor
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marveldb.R
import com.example.marveldb.data.model.ApiImage
import com.example.marveldb.data.model.Character


class CharacterCursorAdapter(private val cursorDatos: Cursor?, private val onClickListener:(Character) -> Unit): RecyclerView.Adapter<CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater =  LayoutInflater.from(parent.context)
        return CharacterViewHolder(layoutInflater.inflate(R.layout.item_movie,parent,false))
    }

    @SuppressLint("Range")
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
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