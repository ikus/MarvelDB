package com.example.marveldb.ui

import android.content.ClipData
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.marveldb.data.CharacterRepository
import com.example.marveldb.data.model.Character
import com.example.marveldb.ui.placeholder.PlaceholderContent
import com.example.marveldb.databinding.FragmentItemDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListFragment]
 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */

@AndroidEntryPoint
class ItemDetailFragment : Fragment() {

    //@Inject
    //internal lateinit var getDetailUseCase: GetDetailUseCase

    @Inject
    internal lateinit var repository: CharacterRepository

    private var characterId : Int = 0
    private var character : Character? = null //TODO: cambiar esat clase

    lateinit var item0DetailTextView: TextView
    lateinit var item1DetailTextView: TextView
    lateinit var item2DetailTextView: TextView
    lateinit var item3DetailTextView: TextView
    lateinit var item4DetailTextView: TextView

    // lateinit var fabFavorite: FloatingActionButton


    lateinit var imageViewDetailMovie: ImageView

    /**
     * The placeholder content this fragment is presenting.
     */
    //private var item: PlaceholderContent.PlaceholderItem? = null

    private var item: Character? = null

    lateinit var itemDetailTextView: TextView
    private var toolbarLayout: CollapsingToolbarLayout? = null

    private var _binding: FragmentItemDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val dragListener = View.OnDragListener { v, event ->
        if (event.action == DragEvent.ACTION_DROP) {
            val clipDataItem: ClipData.Item = event.clipData.getItemAt(0)
            val dragData = clipDataItem.text
            item =null// PlaceholderContent.ITEM_MAP[dragData]
            updateContent()
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the placeholder content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                //item = PlaceholderContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
                CoroutineScope(Dispatchers.IO).launch {
                    item = repository.geCharacterFromApi(it.getInt(ARG_ITEM_ID)).data?.results?.get(0)
                    Log.e("DETALLE:",item?.name!!)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        toolbarLayout = binding.toolbarLayout
        itemDetailTextView = binding.itemDetail


        //item0DetailTextView = binding.itemDetail
        //item1DetailTextView = binding.item1Detail!!
        //item2DetailTextView = binding.item2Detail!!
        //item3DetailTextView = binding.item3Detail!!
        //item4DetailTextView = binding.itemDetail
        //fabFavorite = binding.fab!!


        imageViewDetailMovie = binding.imageViewDetailMovie!!

        updateContent()
        rootView.setOnDragListener(dragListener)

        return rootView
    }

    private fun updateContent() {
        toolbarLayout?.title = item?.name

        // Show the placeholder content as text in a TextView.
        item?.let {
            itemDetailTextView.text = it.description
        }
        val url =item?.thumbnail?.path +"/"+"portrait_uncanny." + item?.thumbnail?.extension
        Glide.with(requireActivity()).load(
            //"https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784/portrait_uncanny.jpg"
        url
        ).into(imageViewDetailMovie)


    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}