package com.ilhamfidatama.moviecatalog

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.ilhamfidatama.moviecatalog.model.Movie
import com.ilhamfidatama.moviecatalog.offline.helper.FavoriteMovieHelper
import com.ilhamfidatama.moviecatalog.present.FavoritePresenter
import com.ilhamfidatama.moviecatalog.present.ModelPresenter
import kotlinx.android.synthetic.main.description_movie.*

@Suppress("PLUGIN_WARNING")
class DescriptionMovieActivity:  AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private var isSaveLocal: Boolean = false
    private val favPresenter = FavoritePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.description_movie)

        val movie = intent.getParcelableExtra(EXTRA_MOVIE) as Movie
        isSaveLocal = FavoriteMovieHelper.findData(movie.id)
        setFavoriteButton()
        movie_title.text = movie.title
        movie_desc.text = movie.overview
        movie_rating.text = movie.popularity.toString()
        val glide = ModelPresenter().loadImage(this, movie.poster_path)
        glide.into(image_movie)

        btnFavorite.setOnClickListener {
            if (isSaveLocal){
                discardFavorite(movie.id)
            }else{
                saveFavorite(movie)
            }
            isSaveLocal = isSaveLocal.not()
            setFavoriteButton()
        }
    }

    private fun setFavoriteButton(){
        if (isSaveLocal){
            btnFavorite.text = getString(R.string.discard_favorite)
        }else{
            btnFavorite.text = getString(R.string.save_favorite)
        }
    }

    private fun saveFavorite(movie: Movie){
        val storage = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageBitmap = (image_movie.drawable as BitmapDrawable).bitmap
        val posterPath = favPresenter.saveImageToStorage(imageBitmap, storage)
        FavoriteMovieHelper.addData(movie.id, movie.title, posterPath, movie.popularity, movie.overview)
    }

    private fun discardFavorite(id: Int){
        FavoriteMovieHelper.deleteData(id)
    }
}