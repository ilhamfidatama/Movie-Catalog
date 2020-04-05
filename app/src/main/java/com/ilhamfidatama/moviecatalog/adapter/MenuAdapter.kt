package com.ilhamfidatama.moviecatalog.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ilhamfidatama.moviecatalog.fragment.*
import com.ilhamfidatama.moviecatalog.helper.SearchHelper
import com.ilhamfidatama.moviecatalog.helper.Searching

class MenuAdapter(private val menu: Array<String>, fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()
        when(position){
            0 -> {
                fragment = ContentMovieFragment().apply {
                    searchHelper = object : SearchHelper{
                        override fun getSearchQuery(): String? = Searching.getQuery()
                    }
                }
            }
            1 -> {
                fragment = ContentTVShowFragment().apply {
                    searchHelper = object : SearchHelper{
                        override fun getSearchQuery(): String? = Searching.getQuery()
                    }
                }
            }
            2 -> fragment = FavFragment()
        }
        return fragment
    }

    override fun getCount(): Int = menu.size
}