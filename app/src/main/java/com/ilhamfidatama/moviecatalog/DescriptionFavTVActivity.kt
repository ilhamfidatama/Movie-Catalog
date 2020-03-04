package com.ilhamfidatama.moviecatalog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ilhamfidatama.moviecatalog.offline.helper.FavoriteTVHelper
import com.ilhamfidatama.moviecatalog.offline.model.FavoriteTV
import com.ilhamfidatama.moviecatalog.present.FavoritePresenter
import kotlinx.android.synthetic.main.activity_description_fav_tv.*

class DescriptionFavTVActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_FAV_TV = "extra_fav_tv"
    }

    private val favPresenter = FavoritePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_fav_tv)

        val tv = intent.getParcelableExtra(EXTRA_FAV_TV) as FavoriteTV
        val imageBitmap = favPresenter.loadImage(tv.posterPath)
        image_fav_tv.setImageBitmap(imageBitmap)
        tittle_fav_tv.text = tv.title
        rating_fav_tv.text = tv.popularity.toString()
        desc_fav_tv.text = tv.overview

        btnDiscard.setOnClickListener {
            FavoriteTVHelper.deleteData(tv.idTV)
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
