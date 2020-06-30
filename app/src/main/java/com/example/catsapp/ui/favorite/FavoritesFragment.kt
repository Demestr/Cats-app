package com.example.catsapp.ui.favorite

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catsapp.CatApplication
import com.example.catsapp.R
import com.example.catsapp.ui.adapters.FavoritesAdapter
import kotlinx.android.synthetic.main.favorites_fragment.*
import kotlinx.android.synthetic.main.list_fragment.*
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    @Inject lateinit var adapter: FavoritesAdapter

    @Inject lateinit var viewModelFactory: FavoritesViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(FavoritesViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as CatApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorites_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler_favorites.layoutManager = LinearLayoutManager(context)
        recycler_favorites.adapter = adapter

        viewModel.cats.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

}