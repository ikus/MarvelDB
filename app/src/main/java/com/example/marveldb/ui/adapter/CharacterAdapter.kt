package com.example.moviedisplay.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marveldb.R
import com.example.marveldb.data.model.Character


class CharacterAdapter(private val listaDatos: List<Character>?, private val onClickListener:(Character) -> Unit): RecyclerView.Adapter<CharacterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater =  LayoutInflater.from(parent.context)
        return CharacterViewHolder(layoutInflater.inflate(R.layout.item_movie,parent,false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item:Character
        item = listaDatos?.get(position)!!
        holder.render(item,onClickListener)
    }

    override fun getItemCount(): Int = listaDatos?.size!!

}