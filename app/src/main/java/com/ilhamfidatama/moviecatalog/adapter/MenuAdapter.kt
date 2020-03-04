package com.ilhamfidatama.moviecatalog.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ilhamfidatama.moviecatalog.fragment.ContentMovieFragment
import com.ilhamfidatama.moviecatalog.fragment.ContentTVShowFragment
import com.ilhamfidatama.moviecatalog.fragment.FavoriteMovieFragment
import com.ilhamfidatama.moviecatalog.fragment.FavoriteTVFragment

class MenuAdapter(private val context: Context, private val menu: Array<String>, fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()
        when(position){
            0 -> fragment = ContentMovieFragment()
            1 -> fragment = ContentTVShowFragment()
            2 -> fragment = FavoriteMovieFragment()
            3 -> fragment = FavoriteTVFragment()
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return menu[position]
    }

    override fun getCount(): Int = menu.size
}