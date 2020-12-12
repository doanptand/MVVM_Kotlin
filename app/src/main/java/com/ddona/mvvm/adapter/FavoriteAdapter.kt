package com.ddona.mvvm.adapter

import android.view.View
import android.view.ViewGroup
import com.ddona.mvvm.R
import com.ddona.mvvm.base.BaseListAdapter
import com.ddona.mvvm.base.BaseViewHolder
import com.ddona.mvvm.extension.inflater
import com.ddona.mvvm.extension.loadImage
import com.ddona.mvvm.model.Pokemon
import kotlinx.android.synthetic.main.item_pokemon.view.*

class FavoriteAdapter : BaseListAdapter<Pokemon>(Pokemon.DiffCallback) {
    override fun contentViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Pokemon> =
        PokemonViewHolder(parent.inflater(R.layout.item_pokemon))

    inner class PokemonViewHolder(itemView: View) : BaseViewHolder<Pokemon>(itemView) {
        override fun bind(item: Pokemon) {
            itemView.tvName.text = item.name
            itemView.imgAvatar.loadImage(item.url)
        }
    }

}

