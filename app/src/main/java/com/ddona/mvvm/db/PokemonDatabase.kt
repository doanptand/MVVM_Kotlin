package com.ddona.mvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ddona.mvvm.model.Pokemon

@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao

    companion object {
        private var INSTANCE: PokemonDatabase? = null

        fun getDatabase(context: Context): PokemonDatabase {
            return INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                PokemonDatabase::class.java,
                "pokemon_db"
            ).build()
        }

        //Advanced
        @Volatile
        private var INSTANCE2: PokemonDatabase? = null
        fun getDatabase2(context: Context): PokemonDatabase {
            return INSTANCE2 ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokemonDatabase::class.java,
                    "pokemon_db"
                ).build()
                INSTANCE2 = instance
                instance//this return instance
            }
        }
    }
}