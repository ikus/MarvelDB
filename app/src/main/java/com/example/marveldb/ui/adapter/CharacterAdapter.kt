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

//    @Inject
//    internal lateinit var repository: CharacterRepository

    //private val maindata: List<Character>? = wrapper.data?.results
    //private val offset:Int = wrapper.data?.offset!!
    //private var index:Int = offset
    //private val limit :Int = wrapper.data?.limit!!
    //private val mainData: Map<Int?, Character>? = wrapper.data?.results?.map{ it -> index++ to it }?.toMap()
    //private val listadeCharacters:List<Character> =wrapper.data?.results!!

    //private val mainData: MutableMap<Int?, Character>? =   mutableMapOf()   //wrapper.data?.results?.map{ it -> index++ to it }?.toMap()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater =  LayoutInflater.from(parent.context)


        /*
        listadeCharacters.forEach { crter ->
            mainData?.put(index++,crter)
        }
*/
        return MovieViewHolder(layoutInflater.inflate(R.layout.item_movie,parent,false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item:Character
        /*

        if(position>= limit!!){
           
        }else{
            item = mainData?.get(position)!! //maindata!![position]
            holder.render(item,onClickListener)
        }
*/
        item = listaDatos?.get(position)!! //maindata!![position]
        holder.render(item,onClickListener)

    }

    override fun getItemCount(): Int = listaDatos?.size!!

}