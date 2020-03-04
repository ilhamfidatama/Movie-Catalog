package com.ilhamfidatama.moviecatalog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ilhamfidatama.moviecatalog.offline.helper.FavoriteMovieHelper
import com.ilhamfidatama.moviecatalog.offline.model.FavoriteMovie
import com.ilhamfidatama.moviecatalog.present.FavoritePresenter
import kotlinx.android.synthetic.main.activity_description_fav_movie.*
import kotlinx.android.synthetic.main.activity_description_fav_movie.btnDiscard
import kotlinx.android.synthetic.main.activity_description_fav_tv.*

class DescriptionFavMovieActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_FAV_MOVIE = "extra_fav_movie"
    }
    private val favoritePresenter = FavoritePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_fav_movie)

        val favMovie = intent.getParcelableExtra(EXTRA_FAV_MOVIE) as FavoriteMovie
        val imageBitmap = FavoritePresenter().loadImage(favMovie.posterPath)
        fav_movie_tittle.text = favMovie.title
        fav_movie_desc.text = favMovie.overview
        fav_movie_rating.text = favMovie.popularity.toString()
        fav_movie_image.setImageBitmap(imageBitmap)

        btnDiscard.setOnClickListener {
            FavoriteMovieHelper.deleteData(favMovie.idMovie)
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
