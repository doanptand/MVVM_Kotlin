package com.ddona.mvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pokemon_id")
    val id: Int,
    @ColumnInfo(name="pokemon_name")
    val name: String,
    @ColumnInfo(name = "pokemon_url")
    val url: String
)
