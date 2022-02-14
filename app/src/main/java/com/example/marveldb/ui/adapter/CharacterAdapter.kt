package com.example.moviedisplay.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marveldb.R
import com.example.marveldb.data.CharacterRepository
import com.example.marveldb.data.model.CharacterDataContainer
import com.example.marveldb.data.model.CharacterDataWrapper
import com.example.marveldb.data.model.Character
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class CharacterAdapter(private val listaDatos: List<Character>?, private val onClickListener:(Character) -> Unit): RecyclerView.Adapter<MovieViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater =  LayoutInflater.from(parent.context)
        return MovieViewHolder(layoutInflater.inflate(R.layout.item_movie,parent,false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item:Character
        item = listaDatos?.get(position)!!
        holder.render(item,onClickListener)
    }

    override fun getItemCount(): Int = listaDatos?.size!!

}