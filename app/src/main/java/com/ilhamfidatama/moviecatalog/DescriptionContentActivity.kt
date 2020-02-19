package com.ilhamfidatama.moviecatalog

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ilhamfidatama.moviecatalog.model.Movie
import kotlinx.android.synthetic.main.description_movie.*

class DescriptionContentActivity:  AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.description_movie)

        val movie = intent.getParcelableExtra(EXTRA_MOVIE) as Movie
        image_movie.setImageResource(movie.image_movie)
        movie_title.text = movie.movie_title
        movie_desc.text = movie.desc_movie
        movie_rating.text = movie.rating_movie
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.language_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.language){
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}