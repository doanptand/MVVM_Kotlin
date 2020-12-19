package com.ddona.mvvm.di

import com.ddona.mvvm.db.PokemonDao
import com.ddona.mvvm.network.PokeApiService
import com.ddona.mvvm.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    fun providePokemonRepository(
        pokemonDao: PokemonDao,
        pokeApiService: PokeApiService
    ): PokemonRepository {
        return PokemonRepository(pokemonDao, pokeApiService)
    }
}