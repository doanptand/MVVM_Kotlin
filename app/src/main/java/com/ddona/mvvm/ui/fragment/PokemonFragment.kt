package com.ddona.mvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ddona.mvvm.R
import com.ddona.mvvm.adapter.PokemonAdapter
import com.ddona.mvvm.viewmodel.PokemonViewModel
import com.ddona.mvvm.viewmodel.PokemonViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_pokemon.*
import timber.log.Timber

class PokemonFragment : Fragment() {
    private val viewModel: PokemonViewModel by viewModels() {
        PokemonViewModelProviderFactory(requireContext())
    }

    private lateinit var pokemonAdapter: PokemonAdapter

    companion object {
        fun newInstance() = PokemonFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupSwipeItem()
        pokemonAdapter = PokemonAdapter(arrayListOf())
        rvPokemon.adapter = pokemonAdapter
        viewModel.getPokemonFromNetwork()
        viewModel.getNetworkPokemonList().observe(requireActivity(), {
            pokemonAdapter.submitData(it)
        })
    }

    private fun setupSwipeItem() {
        val simpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Timber.d("save ${viewHolder.absoluteAdapterPosition} in to database")
                val pokemon = pokemonAdapter.getPokemonAt(viewHolder.absoluteAdapterPosition)
                viewModel.insertPokemon(pokemon)
                pokemonAdapter.notifyItemChanged(viewHolder.absoluteAdapterPosition)
            }
        }
        ItemTouchHelper(simpleCallback).attachToRecyclerView(rvPokemon)
    }
}