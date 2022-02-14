package com.example.moviedisplay.ui.adapter

import android.graphics.Movie
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.marveldb.databinding.ItemMovieBinding
import com.example.marveldb.data.model.Character


class MovieViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemMovieBinding.bind(view)

    fun render(movieModel: Character, onClickListener: (Character) -> Unit){
        val url =movieModel.thumbnail?.path +"/"+"portrait_xlarge." + movieModel.thumbnail?.extension
        Log.e("URL:::",url)
        Glide.with(binding.imageViewMovie.context).load(
            //TODO: cambiar por los valores de cada item
            //"https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/portrait_xlarge.jpg"
            url
        )
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imageViewMovie)

        itemView.setOnClickListener{ onClickListener(movieModel) }
    }
}