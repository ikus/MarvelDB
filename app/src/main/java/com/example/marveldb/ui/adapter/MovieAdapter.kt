package com.example.moviedisplay.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marveldb.R
import com.example.marveldb.data.model.CharacterDataContainer
import com.example.marveldb.data.model.CharacterDataWrapper
import com.example.marveldb.data.model.Character

class MovieAdapter(private val wrapper: CharacterDataWrapper<Character>, private val onClickListener:(Character) -> Unit): RecyclerView.Adapter<MovieViewHolder>() {

    private val maindata: List<Character>? = wrapper.data?.results
    private val limit:Int? =  wrapper.data?.limit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater =  LayoutInflater.from(parent.context)

        return MovieViewHolder(layoutInflater.inflate(R.layout.item_movie,parent,false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item:Character
        if(position>= limit!!){
            //item = wrapper.data!!.results!![position]
            //holder.render(item,onClickListener)
        }else{
            item = maindata!![position]
            holder.render(item,onClickListener)
        }

    }

    override fun getItemCount(): Int = maindata?.size!!//wrapper.data?.total!!

}