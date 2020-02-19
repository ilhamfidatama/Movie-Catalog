package com.ilhamfidatama.moviecatalog.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ilhamfidatama.moviecatalog.R
import com.ilhamfidatama.moviecatalog.fragment.ContentCatalogFragment

class MenuAdapter(private val context: Context, private val menu: Array<String>, fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment = ContentCatalogFragment.newInstance(position)
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return menu[position]
    }

    override fun getCount(): Int = menu.size
}