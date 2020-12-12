package com.ddona.mvvm.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddona.mvvm.db.PokemonDatabase
import com.ddona.mvvm.model.Pokemon
import com.ddona.mvvm.model.PokemonResponse
import com.ddona.mvvm.network.PokemonClient
import com.ddona.mvvm.repository.PokemonRepository
import com.ddona.mvvm.util.IMAGE_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class PokemonViewModel(context: Context) : ViewModel() {
    private val repository: PokemonRepository = PokemonRepository(
        PokemonDatabase.getDatabase(context.applicationContext).pokemonDao(),
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

    private var favoritePokemon: LiveData<List<Pokemon>>

    init {
        favoritePokemon = repository.getFavoritePokemon()
    }


    fun getNetworkPokemonList(): LiveData<List<Pokemon>> = networkPokemon
    fun getFavoriteFromDB() = repository.getFavoritePokemon()
    fun getFavoritePokemonList(): LiveData<List<Pokemon>> = favoritePokemon

    fun insertPokemon(pokemon: Pokemon) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.insertPokemon(pokemon)
        }
    }

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
                        pokemon.url = IMAGE_URL + index[index.size - 2] + ".png"
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