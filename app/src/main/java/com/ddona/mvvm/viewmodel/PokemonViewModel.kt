package com.ddona.mvvm.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddona.mvvm.db.PokemonDatabase
import com.ddona.mvvm.model.Pokemon
import com.ddona.mvvm.model.PokemonResponse
import com.ddona.mvvm.network.PokemonClient
import com.ddona.mvvm.repository.PokemonRepository
import com.ddona.mvvm.util.IMAGE_URL
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
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
        repository.getPokemonList()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { response ->
                Observable.fromIterable(response.pokemonList)
            }
            .map(Pokemon::changeUrl)
            .toList()
            .subscribe { it -> _networkPokemon.postValue(it) }
    }
}