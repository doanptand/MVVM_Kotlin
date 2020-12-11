package com.ddona.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ddona.mvvm.db.PokemonDatabase
import com.ddona.mvvm.model.Pokemon
import com.ddona.mvvm.model.PokemonResponse
import com.ddona.mvvm.network.PokemonClient
import com.ddona.mvvm.repository.PokemonRepository
import com.ddona.mvvm.util.IMAGE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class PokemonViewModel(application: Application) : ViewModel() {
    private val repository: PokemonRepository = PokemonRepository(
        PokemonDatabase.getDatabase(application.applicationContext).pokemonDao(),
        PokemonClient.pokeApiService
    )

    //we can use only one MutableLiveData but due to we don't want other component can edit data, so we create another Live data and expose it instead of MutableLiveData, see below code
    //    private var networkPokemon: MutableLiveData<List<Pokemon>> = MutableLiveData()
    //    fun getNetworkPokemonList(): MutableLiveData<List<Pokemon>> {
    //      return networkPokemon
    //    }
    private var _networkPokemon: MutableLiveData<List<Pokemon>> = MutableLiveData()
    private val networkPokemon: LiveData<List<Pokemon>>
        get() = _networkPokemon

    private var favoritePokemon: LiveData<List<Pokemon>> = repository.getFavoritePokemon()

    init {
        favoritePokemon = repository.getFavoritePokemon()
    }


    fun getNetworkPokemonList(): LiveData<List<Pokemon>> = networkPokemon

    fun getFavoritePokemonList(): LiveData<List<Pokemon>> = favoritePokemon

    fun insertPokemon(pokemon: Pokemon) = repository.insertPokemon(pokemon)

    fun deletePokemon(name: String) = repository.deletePokemon(name)

    fun getPokemonFromNetwork() {
        repository.getPokemonList().enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                val pokemonList = response.body()!!.pokemonList
                if (response.isSuccessful) {
                    for (pokemon in pokemonList) {
                        val url: String = pokemon.url
                        val index = url.split("/")
                        pokemon.url = IMAGE_URL + index[index.size - 1] + ".png"
                        _networkPokemon.postValue(pokemonList)
                        Timber.d("get size is: ${pokemonList.size}")
                    }
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Timber.e(t)
            }

        })
    }
}