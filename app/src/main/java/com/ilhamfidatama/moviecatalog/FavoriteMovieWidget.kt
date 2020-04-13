package com.ilhamfidatama.moviecatalog

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.net.toUri
import com.ilhamfidatama.moviecatalog.adapter.FavMovieWidgetAdapter
import com.ilhamfidatama.moviecatalog.adapter.FavoriteMovieAdapter
import com.ilhamfidatama.moviecatalog.offline.helper.FavoriteMovieHelper

/**
 * Implementation of App Widget functionality.
 */
class FavoriteMovieWidget : AppWidgetProvider() {

    companion object{
        fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val intent = Intent(context, FavMovieWidgetAdapter::class.java)
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            intent.data = intent.toUri(Intent.URI_INTENT_SCHEME).toUri()
            val views = RemoteViews(context.packageName, R.layout.favorite_movie_widget)
            views.setRemoteAdapter(R.id.stack_view_fav_movie, intent)
            val toast = Intent(context, FavoriteMovieWidget::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, toast, PendingIntent.FLAG_UPDATE_CURRENT)
            views.setPendingIntentTemplate(R.id.stack_view_fav_movie, pendingIntent)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}