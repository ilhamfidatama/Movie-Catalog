package com.ilhamfidatama.moviecatalog.factory

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.ilhamfidatama.moviecatalog.R
import com.ilhamfidatama.moviecatalog.offline.helper.FavoriteMovieHelper
import com.ilhamfidatama.moviecatalog.offline.model.FavoriteMovie

class StackViewFavMovieFactory(private val appContext: Context): RemoteViewsService.RemoteViewsFactory {
    private var favMovies = mutableListOf<FavoriteMovie>()
    override fun onCreate() {
        favMovies = FavoriteMovieHelper.getAllData()
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getItemId(position: Int): Long = favMovies[position].id

    override fun onDataSetChanged() {
        favMovies.clear()
        favMovies = FavoriteMovieHelper.getAllData()
    }

    override fun hasStableIds(): Boolean = false

    override fun getViewAt(position: Int): RemoteViews {
        Log.e("widget", favMovies[position].posterPath)
        val remote = RemoteViews(appContext.packageName, R.layout.movie_item_widget)
        val bitmap = BitmapFactory.decodeFile(favMovies[position].posterPath)
        remote.setImageViewBitmap(R.id.movie_poster_widget, bitmap)
        remote.setTextViewText(R.id.movie_tittle_widget, favMovies[position].title)
        return remote
    }

    override fun getCount(): Int = favMovies.size

    override fun getViewTypeCount(): Int = 1

    override fun onDestroy() {

    }
}