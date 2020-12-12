package com.ddona.mvvm.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pokemon_id")
    val id: Int,
    @ColumnInfo(name = "pokemon_name")
    val name: String,
    @ColumnInfo(name = "pokemon_url")
    var url: String
) {
    object DiffCallback : DiffUtil.ItemCallback<Pokemon?>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }

    }
}
