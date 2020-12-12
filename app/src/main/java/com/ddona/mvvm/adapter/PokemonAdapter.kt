package com.ddona.mvvm.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddona.mvvm.R
import com.ddona.mvvm.extension.inflater
import com.ddona.mvvm.extension.loadImage
import com.ddona.mvvm.model.Pokemon
import kotlinx.android.synthetic.main.item_pokemon.view.*
import timber.log.Timber

class PokemonAdapter(private val pokemonList: ArrayList<Pokemon>) :

    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon) {
            itemView.tvName.text = pokemon.name
            Timber.i("load url: ${pokemon.url}")
            itemView.imgAvatar.loadImage(pokemon.url)
        }
    }

    fun submitData(list: List<Pokemon>) {
        pokemonList.clear()
        pokemonList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflater(R.layout.item_pokemon))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(pokemonList[position])

    override fun getItemCount(): Int = pokemonList.size

}