package com.ilhamfidatama.moviecatalog.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ilhamfidatama.moviecatalog.fragment.FavoriteMovieFragment
import com.ilhamfidatama.moviecatalog.fragment.FavoriteTVFragment

class FavMenuAdapter(private val menu: Array<String>, fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()
        when(position){
            0 -> fragment = FavoriteMovieFragment()
            1 -> fragment = FavoriteTVFragment()
        }
        return fragment
    }

    override fun getCount(): Int = menu.size

    override fun getPageTitle(position: Int): CharSequence? = menu[position]
}