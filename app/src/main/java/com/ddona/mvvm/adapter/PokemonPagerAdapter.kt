package com.ddona.mvvm.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ddona.mvvm.ui.fragment.FavoriteFragment
import com.ddona.mvvm.ui.fragment.PokemonFragment

class PokemonPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int = 2
    private val pageTitles = listOf("Pokemon", "Favorite")
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> PokemonFragment.newInstance()
            1 -> FavoriteFragment.newInstance()
            else -> {
                return Fragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pageTitles[position]
    }

}