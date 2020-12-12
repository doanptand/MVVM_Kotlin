package com.ddona.mvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ddona.mvvm.R
import com.ddona.mvvm.adapter.PokemonAdapter
import com.ddona.mvvm.viewmodel.PokemonViewModel
import com.ddona.mvvm.viewmodel.PokemonViewModelProviderFactory
import kotlinx.android.synthetic.main.fragment_pokemon.*

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
        pokemonAdapter = PokemonAdapter(arrayListOf())
        rvPokemon.adapter = pokemonAdapter
        viewModel.getPokemonFromNetwork()
        viewModel.getNetworkPokemonList().observe(requireActivity(), {
            pokemonAdapter.submitData(it)
        })
    }
}