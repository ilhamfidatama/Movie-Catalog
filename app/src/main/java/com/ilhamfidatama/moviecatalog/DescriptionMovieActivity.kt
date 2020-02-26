package com.ilhamfidatama.moviecatalog

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ilhamfidatama.moviecatalog.model.Movie
import com.ilhamfidatama.moviecatalog.present.ModelPresenter
import kotlinx.android.synthetic.main.description_movie.*

class DescriptionMovieActivity:  AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.description_movie)

        val movie = intent.getParcelableExtra(EXTRA_MOVIE) as Movie
        Log.w("movie", "$movie")
//        image_movie.setImageResource(movie.image_movie)
        movie_title.text = movie.title
        movie_desc.text = movie.overview
        movie_rating.text = movie.popularity.toString()
        val glide = ModelPresenter().loadImage(this, movie.poster_path)
        glide.into(image_movie)
    }
}