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
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.marveldb.R
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marveldb.CharactersMarvelApp.Companion.prefs
import com.example.marveldb.data.CharacterRepository
import com.example.marveldb.databinding.FragmentItemListBinding

import com.example.marveldb.data.model.Character
import com.example.moviedisplay.ui.adapter.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ItemListFragment : Fragment() {

    @Inject
    internal lateinit var getCharactersUseCase: GetCharactersUseCase

    @Inject
    internal lateinit var repository: CharacterRepository

    private lateinit var nestedScrollView:NestedScrollView
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private var page:Int =0
    private var limit:Int =20

    private var listObjetos:MutableList<Character> = emptyList<Character>().toMutableList()

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
        recyclerView =  binding.itemList
        nestedScrollView = view.findViewById(R.id.item_list_container)
        progressBar = view.findViewById(R.id.progress_bar)

        val itemDetailFragmentContainer: View? = view.findViewById(R.id.item_detail_nav_container)

        setupRecyclerView(recyclerView, itemDetailFragmentContainer)

    }

    fun onItemSelected(movie:  com.example.marveldb.data.model.Character){
        //TODO: Esta es la parte que hayq ue refactorizar.
        val bundle = Bundle()
        bundle.putInt(
            ItemDetailFragment.ARG_ITEM_ID,
            movie.id!!
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
            listObjetos = result.data?.results as MutableList<Character>
            Log.e("INFO:::","iS NOT EMPTY"+result)

            activity?.runOnUiThread{
                if ( result !=null ) {
                    val manager = GridLayoutManager(activity,2 )
                    recyclerView.layoutManager = manager
                    recyclerView.adapter = CharacterAdapter(listObjetos) { character ->
                        onItemSelected(
                            character
                        )
                    }
                    //recyclerView.addItemDecoration(decoration)
                    getData(page,limit)
                    nestedScrollView.setOnScrollChangeListener(object: NestedScrollView.OnScrollChangeListener{
                        override fun onScrollChange(
                            v: NestedScrollView?,
                            scrollX: Int,
                            scrollY: Int,
                            oldScrollX: Int,
                            oldScrollY: Int
                        ) {
                            //TODO("Not yet implemented")
                            if(scrollY== (v?.getChildAt(0)?.measuredHeight!! - v?.measuredHeight!!)){
                                page++
                                //Swhoe progress bar
                                getData(page,limit)
                            }
                        }

                    } )
                }else{
                    //TODO:Show error
                }
            }

        }
    }

    private fun getData(page: Int, limit: Int) {
        var offset= page*100;
        var pagina = page
        var temporal:List<Character>
        var count = 0
        var dbupdated=prefs.getDBUpdated()
        if(!dbupdated){
            showProgressbar(true)
            CoroutineScope(Dispatchers.IO).launch {
                do{
                    Log.e("PAGE:::",pagina.toString())
                    Log.e("OFFSET:::",offset.toString())
                    var result = repository.getAllCharactersFromApi(offset,100)
                    count = result.data?.count!!
                    if(count>0)
                        repository.insertCharacters(result.data?.results ?: emptyList())
                    pagina++
                    offset = pagina * 100
                } while(count>0)
                prefs.saveDBUpdated(true)

            }
        }else{//TODO: get form DB info
            CoroutineScope(Dispatchers.IO).launch {
                var result =   repository.getAllCharactersFromDatabase(100,pagina * 100)
                listObjetos.addAll(result)
                activity?.runOnUiThread{
                    recyclerView.adapter = CharacterAdapter(listObjetos) { character ->
                        onItemSelected(
                            character
                        )
                    }
                }
            }
        }
        showProgressbar(false)
    }


    fun showProgressbar(show:Boolean){
        activity?.runOnUiThread {
            progressBar.isVisible =show;
            if(show==true) progressBar.visibility =   View.VISIBLE
            else  progressBar.visibility = View.INVISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

