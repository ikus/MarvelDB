package com.example.moviedisplay.ui.adapter

import android.graphics.Movie
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marveldb.databinding.ItemMovieBinding

//import com.example.moviedisplay.databinding.ItemMovieBinding
//import com.example.moviedisplay.domain.model.Movie
import com.example.marveldb.data.model.Character


class MovieViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemMovieBinding.bind(view)

    fun render(movieModel: Character, onClickListener: (Character) -> Unit){
        val url =movieModel.thumbnail
        Glide.with(binding.imageViewMovie.context).load(
            //TODO: cambiar por los valores de cada item
            //"http://image.tmdb.org/t/p/w185/"+movieModel.poster_path+"?api_key=28b80b41ebf312e0ba2909f4472d67b6"
            "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/portrait_xlarge.jpg"
        ).into(binding.imageViewMovie)

        itemView.setOnClickListener{ onClickListener(movieModel) }
    }
}