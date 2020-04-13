package com.ilhamfidatama.moviecatalog.adapter

import android.content.Intent
import android.widget.RemoteViewsService
import com.ilhamfidatama.moviecatalog.factory.StackViewFavMovieFactory
import com.ilhamfidatama.moviecatalog.offline.helper.FavoriteMovieHelper

class FavMovieWidgetAdapter: RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        val favMovies = FavoriteMovieHelper.getAllData()
        return StackViewFavMovieFactory(this.applicationContext)
    }
}