package com.example.moviedisplay.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.marveldb.databinding.ItemMovieBinding
import com.example.marveldb.data.model.Character


class CharacterViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding = ItemMovieBinding.bind(view)

    fun render(character: Character, onClickListener: (Character) -> Unit){
        val url =character.thumbnail?.path +"/"+"portrait_xlarge." + character.thumbnail?.extension
        Glide.with(binding.imageViewMovie.context).load(
            url
        )
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imageViewMovie)
        itemView.setOnClickListener{ onClickListener(character) }
        //binding.textViewCharacterName.text = character.name
    }
}