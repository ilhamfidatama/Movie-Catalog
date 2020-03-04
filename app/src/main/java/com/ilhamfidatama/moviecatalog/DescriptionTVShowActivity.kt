package com.ilhamfidatama.moviecatalog

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ilhamfidatama.moviecatalog.model.TVShow
import com.ilhamfidatama.moviecatalog.offline.helper.FavoriteTVHelper
import com.ilhamfidatama.moviecatalog.present.FavoritePresenter
import com.ilhamfidatama.moviecatalog.present.ModelPresenter
import kotlinx.android.synthetic.main.description_tv_show.*

@Suppress("PLUGIN_WARNING")
class DescriptionTVShowActivity: AppCompatActivity() {
    companion object{
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    private var isSave: Boolean = false
    private val favPresenter = FavoritePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.description_tv_show)

        val tvShow = intent.getParcelableExtra(EXTRA_TV_SHOW) as TVShow
        isSave = FavoriteTVHelper.findData(tvShow.id)
        tittle_tv.text = tvShow.name
        desc_tv.text = tvShow.overview
        rating_tv.text = tvShow.popularity.toString()
        val glide = ModelPresenter().loadImage(this, tvShow.poster_path)
        glide.into(image_tv)

        btnFavorite.setOnClickListener {
            if (isSave){
                discardFavorite(tvShow.id)
            }else{
                saveFavorite(tvShow)
            }
            isSave = isSave.not()
            setFavoriteButton()
        }
    }

    private fun setFavoriteButton(){
        if (isSave){
            btnFavorite.text = getString(R.string.discard_favorite)
        }else{
            btnFavorite.text = getString(R.string.save_favorite)
        }
    }

    private fun saveFavorite(tvShow: TVShow){
        val storage = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageBitmap = (image_tv.drawable as BitmapDrawable).bitmap
        val posterPath = favPresenter.saveImageToStorage(imageBitmap, storage)
        FavoriteTVHelper.addData(tvShow.id, tvShow.name, posterPath, tvShow.popularity, tvShow.overview)
    }

    private fun discardFavorite(id: Int){
        FavoriteTVHelper.deleteData(id)
    }
}