package com.example.marveldb.ui

import android.content.ClipData
import android.content.ClipDescription
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.marveldb.R
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marveldb.ui.placeholder.PlaceholderContent;
import com.example.marveldb.databinding.FragmentItemListBinding
import com.example.marveldb.databinding.ItemListContentBinding
import com.example.moviedisplay.ui.adapter.MovieAdapter
import com.example.marveldb.data.model.Character
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ItemListFragment : Fragment() {

    @Inject
    internal lateinit var getCharactersUseCase: GetCharactersUseCase

    private val unhandledKeyEventListenerCompat =
        ViewCompat.OnUnhandledKeyEventListenerCompat { v, event ->
            if (event.keyCode == KeyEvent.KEYCODE_Z && event.isCtrlPressed) {
                Toast.makeText(
                    v.context,
                    "Undo (Ctrl + Z) shortcut triggered",
                    Toast.LENGTH_LONG
                ).show()
                true
            } else if (event.keyCode == KeyEvent.KEYCODE_F && event.isCtrlPressed) {
                Toast.makeText(
                    v.context,
                    "Find (Ctrl + F) shortcut triggered",
                    Toast.LENGTH_LONG
                ).show()
                true
            }
            false
        }

    private var _binding: FragmentItemListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.addOnUnhandledKeyEventListener(view, unhandledKeyEventListenerCompat)

        val recyclerView: RecyclerView = binding.itemList

        // Leaving this not using view binding as it relies on if the view is visible the current
        // layout configuration (layout, layout-sw600dp)
        val itemDetailFragmentContainer: View? = view.findViewById(R.id.item_detail_nav_container)

        setupRecyclerView(recyclerView, itemDetailFragmentContainer)

    }

    fun onItemSelected(movie:  com.example.marveldb.data.model.Character){
        /*
        Toast.makeText(
            activity,
            movie.title,
            Toast.LENGTH_SHORT
        ).show()
        */
        //TODO: Esta es la parte que hayq ue refactorizar.
        //val item = itemView.tag as PlaceholderContent.PlaceholderItem

        val bundle = Bundle()
        bundle.putString(
            ItemDetailFragment.ARG_ITEM_ID,
            "1"//movie.id.toString()
        )

        findNavController().navigate(R.id.show_item_detail, bundle)
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        itemDetailFragmentContainer: View?
    ) {

        CoroutineScope(Dispatchers.IO).launch {
            //isLoading.postValue(true)
            val result = getCharactersUseCase()
            Log.e("INFO:::","iS NOT EMPTY"+result)


            activity?.runOnUiThread{
                if ( result !=null ) {
                    //movieModel.postValue(result[0])
                    //isLoading.postValue(false)

                    //result[0].posterPath.



                    //var maviesdominio =  result.map { it.toDomain() }

                    //Log.e("INFO:::",maviesdominio.toString())

                    //val manager = LinearLayoutManager(activity)

                    val manager = GridLayoutManager(activity,2 )
                    //val decoration = DividerItemDecoration(activity, manager.orientation)

                    recyclerView.layoutManager = manager



                    recyclerView.adapter = MovieAdapter(result/*emptyList()*/ /*MovieProvider.movieList*/) { character ->
                        onItemSelected(
                            character
                        )
                    }

                    //recyclerView.addItemDecoration(decoration)

                }else{
                    //TODO:Show error
                }
            }




            /*
            runOnUiThread{
                if (!result.isNullOrEmpty()) {
                    //movieModel.postValue(result[0])
                    //isLoading.postValue(false)

                    //result[0].posterPath.


                /*
                    var maviesdominio =  result.map { it.toDomain() }
                    Log.e("INFO:::",maviesdominio.toString())

                    val manager = LinearLayoutManager(applicationContext)
                    val decoration = DividerItemDecoration(applicationContext, manager.orientation)
                    binding.recyvlerViewMovies.layoutManager = manager

                    binding.recyvlerViewMovies.adapter = MovieAdapter(maviesdominio/*emptyList()*/ /*MovieProvider.movieList*/) { movie ->
                        onItemSelected(
                            movie
                        )
                    }
                    binding.recyvlerViewMovies.addItemDecoration(decoration)
                    */
                }else{
                    //TODO:Show error
                }
            }

             */


        }


/*
        CoroutineScope(Dispatchers.IO).launch {
            //isLoading.postValue(true)
            val result = getCharactersUseCase()
        }
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(
            PlaceholderContent.ITEMS, itemDetailFragmentContainer
        )

 */
    }

    class SimpleItemRecyclerViewAdapter(
        private val values: List<PlaceholderContent.PlaceholderItem>,
        private val itemDetailFragmentContainer: View?
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val binding =
                ItemListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.idView.text = item.id
            holder.contentView.text = item.content

            with(holder.itemView) {
                tag = item
                setOnClickListener { itemView ->
                    val item = itemView.tag as PlaceholderContent.PlaceholderItem
                    val bundle = Bundle()
                    bundle.putString(
                        ItemDetailFragment.ARG_ITEM_ID,
                        item.id
                    )
                    if (itemDetailFragmentContainer != null) {
                        itemDetailFragmentContainer.findNavController()
                            .navigate(R.id.fragment_item_detail, bundle)
                    } else {
                        itemView.findNavController().navigate(R.id.show_item_detail, bundle)
                    }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    /**
                     * Context click listener to handle Right click events
                     * from mice and trackpad input to provide a more native
                     * experience on larger screen devices
                     */
                    setOnContextClickListener { v ->
                        val item = v.tag as PlaceholderContent.PlaceholderItem
                        Toast.makeText(
                            v.context,
                            "Context click of item " + item.id,
                            Toast.LENGTH_LONG
                        ).show()
                        true
                    }
                }

                setOnLongClickListener { v ->
                    // Setting the item id as the clip data so that the drop target is able to
                    // identify the id of the content
                    val clipItem = ClipData.Item(item.id)
                    val dragData = ClipData(
                        v.tag as? CharSequence,
                        arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                        clipItem
                    )

                    if (Build.VERSION.SDK_INT >= 24) {
                        v.startDragAndDrop(
                            dragData,
                            View.DragShadowBuilder(v),
                            null,
                            0
                        )
                    } else {
                        v.startDrag(
                            dragData,
                            View.DragShadowBuilder(v),
                            null,
                            0
                        )
                    }
                }
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(binding: ItemListContentBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val idView: TextView = binding.idText
            val contentView: TextView = binding.content
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}