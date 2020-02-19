package com.ilhamfidatama.moviecatalog.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilhamfidatama.moviecatalog.model.Movie

import com.ilhamfidatama.moviecatalog.R
import com.ilhamfidatama.moviecatalog.adapter.MovieAdapter
import kotlinx.android.synthetic.main.fragment_content_catalog.*

class ContentCatalogFragment : Fragment() {
    private var menuSelected: Int = 0
    private lateinit var contextActivity: Context

    companion object {
        @JvmStatic
        fun newInstance(selected: Int) =
            ContentCatalogFragment().apply {
                menuSelected = selected
            }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context != null) {
            contextActivity = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contentCatalog = getContent()
        listContent.layoutManager =LinearLayoutManager(contextActivity)
        listContent.adapter = MovieAdapter(contextActivity, contentCatalog)
    }

    private fun getContent(): MutableList<Movie>{
        var content = mutableListOf<Movie>()

        when(menuSelected){
            0 -> content = getMovie()
            1 -> content = getTVSeries()
        }

        return content
    }

    private fun getTVSeries(): MutableList<Movie>{
        var tvSeries = mutableListOf<Movie>()
        val moviesTitle = resources.getStringArray(R.array.tv_series_title)
        val moviesRating = resources.getStringArray(R.array.tv_series_rating)
        val moviesDesc = resources.getStringArray(R.array.tv_series_description)
        val moviesImage = resources.obtainTypedArray(R.array.tv_series_image)
//        moviesImage.recycle()

        for (position in moviesTitle.indices){
            val movie = Movie(
                moviesImage.getResourceId(position, -1),
                moviesTitle[position],
                moviesRating[position],
                moviesDesc[position]
            )
            tvSeries.add(movie)
        }
        return tvSeries
    }

    private fun getMovie(): MutableList<Movie>{
        var movies = mutableListOf<Movie>()
        val moviesTitle = resources.getStringArray(R.array.movies_title)
        val moviesRating = resources.getStringArray(R.array.movies_rating)
        val moviesDesc = resources.getStringArray(R.array.movies_description)
        val moviesImage = resources.obtainTypedArray(R.array.movies_image)
//        moviesImage.recycle()

        for (position in moviesTitle.indices){
            val movie = Movie(
                moviesImage.getResourceId(position, -1),
                moviesTitle[position],
                moviesRating[position],
                moviesDesc[position]
            )
            movies.add(movie)
        }
        return movies
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_content_catalog, container, false)
    }
}
