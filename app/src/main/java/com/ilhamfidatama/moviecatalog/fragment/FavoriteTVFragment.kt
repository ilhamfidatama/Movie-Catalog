package com.ilhamfidatama.moviecatalog.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilhamfidatama.moviecatalog.R
import com.ilhamfidatama.moviecatalog.adapter.FavoriteTVAdapter
import com.ilhamfidatama.moviecatalog.present.ContentPresenter
import com.ilhamfidatama.moviecatalog.present.FavoritePresenter
import kotlinx.android.synthetic.main.fragment_content_catalog.*

class FavoriteTVFragment: Fragment() {

    private lateinit var contextActivity: Context
    private lateinit var adapter: FavoriteTVAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contextActivity = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProgressbar(true)
        adapter = FavoriteTVAdapter(contextActivity)
        adapter.notifyDataSetChanged()
        listContent.setHasFixedSize(true)
        listContent.layoutManager = LinearLayoutManager(contextActivity)
        listContent.adapter = adapter
        getFavTV()
    }

    private fun getFavTV(){
        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(FavoritePresenter::class.java)
        viewModel.loadFavTV()
        viewModel.getFavTV().observe(this, Observer {
            it?.let {
                adapter.addData(it)
                setProgressbar(false)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_content_catalog, container, false)
    }

    private fun setProgressbar(show: Boolean){
        ContentPresenter().setProgressBar(progressBar, show)
    }

}