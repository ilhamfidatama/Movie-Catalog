package com.ilhamfidatama.moviecatalog.receivers

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.ilhamfidatama.moviecatalog.BuildConfig
import com.ilhamfidatama.moviecatalog.R
import com.ilhamfidatama.moviecatalog.api.Api
import com.ilhamfidatama.moviecatalog.api.service.BaseResponseMovie
import com.ilhamfidatama.moviecatalog.present.ModelPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class NotifReceiver: BroadcastReceiver(){
    private val dailyNotification = 12
    private val releaseNotification = 11

    companion object{
        const val NOTIF_TYPE = "notif type"
        val CHANNEL_ID = "movieCatalog01"
        val CHANNEL_NAME ="Movie Catalog Channel"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val type = intent.getStringExtra(NOTIF_TYPE)
        val dailyType = context.resources.getString(R.string.daily_notif)
        val releaseType = context.resources.getString(R.string.release_notif)
        Log.e("receive", "$type")
        when(type){
            dailyType -> createDailyNotif(context)
            releaseType -> getReleased(context)
        }
    }

    private fun createDailyNotif(context: Context){
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder =NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(context.getString(R.string.app_name))
            .setContentText(context.getString(R.string.notification_message))
            .setSmallIcon(R.drawable.baseline_movie_black_24dp)
            .setAutoCancel(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            builder.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }
        val notification = builder.build()
        notificationManager.notify(dailyNotification, notification)
    }

    private fun createReleaseNotif(context: Context, released: Int){
        Log.e("receive", "release notification")
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(context.getString(R.string.release_tittle))
            .setContentText("$released movie release")
            .setSmallIcon(R.drawable.baseline_movie_black_24dp)
            .setAutoCancel(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            builder.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }
        val notification = builder.build()
        notificationManager.notify(releaseNotification, notification)
    }

    private fun getReleased(context: Context){
        val api = BuildConfig.API_KEY
        val date = getDate()
        val service = Api.create()
        service.releaseMovie(api, date, date, context.getString(R.string.language_api)).enqueue(object :
            Callback<BaseResponseMovie> {
            override fun onFailure(call: Call<BaseResponseMovie>, t: Throwable) {
                Log.e("release-movie", "${t.message}")
            }

            override fun onResponse(
                call: Call<BaseResponseMovie>,
                response: Response<BaseResponseMovie>
            ) {
                response.body()?.results?.let {
                    if (it.size != 0){
                        createReleaseNotif(context, it.size)
                    }
                }
            }

        })
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(): String{
        val date = Date()
        val parse = SimpleDateFormat("yyyy-MM-dd")
        return parse.format(date)
    }

}
