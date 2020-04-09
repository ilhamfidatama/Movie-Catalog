package com.ilhamfidatama.moviecatalog.fragment

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.ilhamfidatama.moviecatalog.R
import com.ilhamfidatama.moviecatalog.receivers.NotifReceiver
import com.ilhamfidatama.moviecatalog.receivers.NotifReceiver.Companion.NOTIF_TYPE
import java.util.*

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var appContext: Context
    private lateinit var dailyPreferences: SwitchPreferenceCompat
    private lateinit var releasePreferences: SwitchPreferenceCompat
    private lateinit var dailyType: String
    private lateinit var releaseType: String
    private lateinit var dailyKey: String
    private lateinit var releaseKey: String
    private val dailyNotification = 12
    private val releaseNotification = 11

    override fun onAttach(context: Context) {
        appContext = context
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        dailyKey = appContext.resources.getString(R.string.reminder_key)
        releaseKey = appContext.resources.getString(R.string.release_key)
        dailyType = appContext.resources.getString(R.string.daily_notif)
        releaseType = appContext.resources.getString(R.string.release_notif)
        dailyPreferences = findPreference<Preference>(dailyKey) as SwitchPreferenceCompat
        releasePreferences = findPreference<Preference>(releaseKey) as SwitchPreferenceCompat
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        Log.e("changed", "$key")
        when(key){
            dailyKey -> {
                if (dailyPreferences.isChecked){
                    enableNotification(dailyType, dailyNotification,7, 0, 1)
                }else{
                    disableNotification(dailyNotification)
                }
            }
            releaseKey -> {
                if (releasePreferences.isChecked){
                    enableNotification(releaseType, releaseNotification, 7, 5, 1)
                }else{
                    disableNotification(releaseNotification)
                }
            }
        }
    }

    private fun enableNotification(type: String, broadcastID: Int, hour: Int, minute: Int, second: Int){
        Log.e("setting daily", "enable notif $type ${Calendar.getInstance().time}")
        val alarmManager = appContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(appContext, NotifReceiver::class.java)
        intent.putExtra(NOTIF_TYPE, type)
        val calendar = getCalendar(hour, minute, second)
        val pendingIntent = PendingIntent.getBroadcast(appContext, broadcastID, intent, 0)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
    }

    private fun disableNotification(broadcastID: Int){
        val alarmManager = appContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(appContext, NotifReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(appContext, broadcastID, intent, 0)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
    }

    private fun getCalendar(hour: Int, minute: Int, second: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, second)
        return calendar
    }
}