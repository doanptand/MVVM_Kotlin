package com.ddona.mvvm.repository

import androidx.lifecycle.LiveData
import com.ddona.mvvm.db.PokemonDao
import com.ddona.mvvm.model.Pokemon
import com.ddona.mvvm.model.PokemonResponse
import com.ddona.mvvm.network.PokeApiService
import retrofit2.Call


class PokemonRepository(
    private val pokemonDao: PokemonDao,
    private val pokemonApiService: PokeApiService
) {
    fun getPokemonList(): Call<PokemonResponse> {
        return pokemonApiService.getPokemonList()
    }

    fun insertPokemon(pokemon: Pokemon) {
        pokemonDao.insertPokemon(pokemon)
    }

    fun deletePokemon(pokemonName: String?) {
        pokemonDao.deletePokemon(pokemonName!!)
    }

    fun deleteAllPokemon() {
        pokemonDao.deleteAllPokemon()
    }

    fun getFavoritePokemon(): LiveData<List<Pokemon>> {
        return pokemonDao.getFavoritePokemonList()
    }
}