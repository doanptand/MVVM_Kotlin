package com.ddona.mvvm.network

import com.ddona.mvvm.util.BASE_URL
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonClient {
    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        val pokeApiService: PokeApiService = retrofit.create(PokeApiService::class.java)
    }
}


object PokemonClient2 {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val pokeApiService: PokeApiService = retrofit.create(PokeApiService::class.java)

}