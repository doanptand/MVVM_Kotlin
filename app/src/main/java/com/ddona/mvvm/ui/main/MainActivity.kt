package com.ddona.mvvm.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ddona.mvvm.R
import com.ddona.mvvm.model.PokemonResponse
import com.ddona.mvvm.network.PokemonClient
import com.ddona.mvvm.network.PokemonClient2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //FIXME this is only for test, we use MVVM architecture, so we'll move below code to ViewModel
        PokemonClient2.pokeApiService.getPokemonList().enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                if (response.isSuccessful) {
                    Timber.d("Size is ${(response.body() as PokemonResponse).pokemonList.size}")
                } else {
                    Timber.d("Un-success: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Timber.e(t)
            }
        })

        PokemonClient.pokeApiService.getPokemonList().enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                if (response.isSuccessful) {
                    Timber.d("Size 2 is ${(response.body() as PokemonResponse).pokemonList.size}")
                } else {
                    Timber.d("Un-success 2: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Timber.e(t)
            }
        })
    }
}