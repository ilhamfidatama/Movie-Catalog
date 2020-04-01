package com.ilhamfidatama.moviecatalog.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.ilhamfidatama.moviecatalog.R
import com.ilhamfidatama.moviecatalog.adapter.FavMenuAdapter
import kotlinx.android.synthetic.main.fav_fragment.*

class FavFragment: Fragment() {
    private lateinit var contextActivity: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contextActivity = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menu = resources.getStringArray(R.array.fav_menu)
        val adapter = FavMenuAdapter(menu, requireFragmentManager())
        contentApp.adapter = adapter
        tabsMenu.setupWithViewPager(contentApp)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fav_fragment, container, false)
    }
}