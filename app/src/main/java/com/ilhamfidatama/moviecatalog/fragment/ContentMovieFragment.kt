package com.ilhamfidatama.moviecatalog.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilhamfidatama.moviecatalog.R
import com.ilhamfidatama.moviecatalog.adapter.MovieAdapter
import com.ilhamfidatama.moviecatalog.present.ContentPresenter
import com.ilhamfidatama.moviecatalog.present.ModelPresenter
import kotlinx.android.synthetic.main.fragment_content_catalog.*

class ContentMovieFragment : Fragment() {
    private lateinit var contextActivity: Context
    private lateinit var adapter: MovieAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context?.let {
            contextActivity = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProgressBar(true)
        Log.w("bug", "movie fragment")
        adapter = MovieAdapter(contextActivity)
        adapter.notifyDataSetChanged()
        listContent.setHasFixedSize(true)
        listContent.layoutManager =LinearLayoutManager(contextActivity)
        listContent.adapter = adapter
        getMovie()
    }

    private fun getMovie(){
        val movieModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ModelPresenter::class.java)
        movieModel.getMovie(getString(R.string.language_api))
        movieModel.getListMovie().observe(this, Observer {
            it?.let {
                adapter.addData(it)
                setProgressBar(false)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_content_catalog, container, false)
    }

    private fun setProgressBar(show: Boolean){
        ContentPresenter().setProgressBar(progressBar, show)
    }
}
