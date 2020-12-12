package com.ddona.mvvm.network

import com.ddona.mvvm.model.PokemonResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET

interface PokeApiService {
    @GET("pokemon")
    fun getPokemonList(): Observable<PokemonResponse>
}