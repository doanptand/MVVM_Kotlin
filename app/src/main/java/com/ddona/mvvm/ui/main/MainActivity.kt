package com.ddona.mvvm.ui.main

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ddona.mvvm.R
import com.ddona.mvvm.viewmodel.PokemonViewModel
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    class PokemonViewModelProviderFactory(private val application: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PokemonViewModel(application) as T
        }

    }

    //cach khoi tao viewmodel thu 1
    private val viewModel2: PokemonViewModel by viewModels() {
        PokemonViewModelProviderFactory(application)
    }

    //Cach khoi tao viewmodel thu 2
    //private lateinit var viewModel: PokemonViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel2.getPokemonFromNetwork()
        viewModel2.getNetworkPokemonList().observe(this, {
            Timber.d("get size is ${it.size}")
        })


        //Cach khoi tao viewmodel thu 2
//        viewModel = ViewModelProvider(this, PokemonViewModelProviderFactory(application)).get(
//            PokemonViewModel::class.java
//        )
//        viewModel.getPokemonFromNetwork()
//        viewModel.getNetworkPokemonList().observe(this, {
//            Timber.d("get size is ${it.size}")
//        })


    }
}