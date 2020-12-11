package com.ddona.mvvm.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ddona.mvvm.model.Pokemon

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(pokemon: Pokemon)

    @Query("DELETE FROM favorite where pokemon_name = :name")
    fun deletePokemon(name:String)

    @Query("DELETE FROM favorite")
    fun deleteAllPokemon()

    @Query("SELECT * FROM favorite")
    fun getFavoritePokemonList() : LiveData<List<Pokemon>>
}