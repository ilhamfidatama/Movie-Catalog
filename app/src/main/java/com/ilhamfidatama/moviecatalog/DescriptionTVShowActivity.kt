package com.ilhamfidatama.moviecatalog

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ilhamfidatama.moviecatalog.model.TVShow
import com.ilhamfidatama.moviecatalog.present.ModelPresenter
import kotlinx.android.synthetic.main.description_tv_show.*

class DescriptionTVShowActivity: AppCompatActivity() {
    companion object{
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.description_tv_show)

        val tvShow = intent.getParcelableExtra(EXTRA_TV_SHOW) as TVShow
        tittle_tv.text = tvShow.name
        desc_tv.text = tvShow.overview
        rating_tv.text = tvShow.popularity.toString()
        val glide = ModelPresenter().loadImage(this, tvShow.poster_path)
        glide.into(image_tv)
    }
}