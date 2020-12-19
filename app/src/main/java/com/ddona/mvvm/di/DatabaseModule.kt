package com.ddona.mvvm.di

import android.app.Application
import androidx.room.Room
import com.ddona.mvvm.db.PokemonDao
import com.ddona.mvvm.db.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providePokemonDB(application: Application): PokemonDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            PokemonDatabase::class.java,
            "pokemon_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providePokemonDao(db: PokemonDatabase): PokemonDao {
        return db.pokemonDao()
    }
}