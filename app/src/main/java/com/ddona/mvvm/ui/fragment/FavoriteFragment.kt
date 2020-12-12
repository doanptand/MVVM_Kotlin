package com.ddona.mvvm.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ddona.mvvm.R
import com.ddona.mvvm.viewmodel.PokemonViewModel
import com.ddona.mvvm.viewmodel.PokemonViewModelProviderFactory
import timber.log.Timber

class FavoriteFragment : Fragment() {
    companion object {
        fun newInstance(): FavoriteFragment = FavoriteFragment()
    }

    private lateinit var viewModel: PokemonViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, PokemonViewModelProviderFactory(requireContext())).get(
            PokemonViewModel::class.java
        )
        viewModel.getFavoritePokemonList().observe(requireActivity(), {
            Timber.d("get local pokemon ${it.size}")
        })
    }

}