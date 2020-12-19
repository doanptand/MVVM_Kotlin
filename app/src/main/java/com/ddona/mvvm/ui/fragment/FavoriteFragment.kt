package com.ddona.mvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import com.ddona.mvvm.R
import com.ddona.mvvm.adapter.FavoriteAdapter
import com.ddona.mvvm.extension.setupSwipeItem
import com.ddona.mvvm.viewmodel.PokemonViewModel
import com.ddona.mvvm.viewmodel.PokemonViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_favorite.*
import timber.log.Timber

class FavoriteFragment : Fragment() {
    companion object {
        fun newInstance(): FavoriteFragment = FavoriteFragment()
    }

    private lateinit var viewModel: PokemonViewModel
    private lateinit var adapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = FavoriteAdapter()
        rvFavorite.adapter = adapter
        viewModel = ViewModelProvider(this, PokemonViewModelProviderFactory(requireContext())).get(
            PokemonViewModel::class.java
        )
        viewModel.getFavoriteFromDB()
        viewModel.getFavoritePokemonList().observe(requireActivity(), {
            //FIXME observe not called automaticlly after pokemon saved to favorite list.
            //Database was inserted but observe not called
            Timber.d("get local pokemon ${it.size}")
            adapter.submitList(it)
        })
        rvFavorite.setupSwipeItem(0, ItemTouchHelper.RIGHT, swipeAction = {
            Timber.d("save p=$it in to database")
            val pokemon = adapter.currentList[it]
            viewModel.deletePokemon(pokemon.name)
            adapter.notifyItemChanged(it)
        }, dragAction = { false })
    }

}