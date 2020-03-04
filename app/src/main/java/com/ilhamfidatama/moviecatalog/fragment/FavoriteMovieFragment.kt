package com.ilhamfidatama.moviecatalog.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilhamfidatama.moviecatalog.R
import com.ilhamfidatama.moviecatalog.adapter.FavoriteMovieAdapter
import com.ilhamfidatama.moviecatalog.present.ContentPresenter
import com.ilhamfidatama.moviecatalog.present.FavoritePresenter
import kotlinx.android.synthetic.main.fragment_content_catalog.*

class FavoriteMovieFragment: Fragment() {
    private lateinit var contextActivity: Context
    private lateinit var adapter: FavoriteMovieAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.let {
            contextActivity = it
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProgressBar(true)
        adapter = FavoriteMovieAdapter(contextActivity)
        adapter.notifyDataSetChanged()
        listContent.setHasFixedSize(true)
        listContent.layoutManager = LinearLayoutManager(contextActivity)
        listContent.adapter = adapter
        getFavMovie()
    }

    fun getFavMovie(){
        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(FavoritePresenter::class.java)
        viewModel.loadFavMovie()
        viewModel.getFavMovie().observe(this, Observer {
            it?.let {
                adapter.addData(it)
                setProgressBar(false)
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

    private fun setProgressBar(show: Boolean){
        ContentPresenter().setProgressBar(progressBar, show)
    }
}