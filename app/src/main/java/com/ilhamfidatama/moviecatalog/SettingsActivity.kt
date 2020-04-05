package com.ilhamfidatama.moviecatalog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreferenceCompat
import com.ilhamfidatama.moviecatalog.helper.ReminderHelper

class SettingsActivity : AppCompatActivity() {

    companion object {
        private val summaryListener = Preference.OnPreferenceChangeListener { preference, newValue ->
            if (preference.key == "remind_me"){
                ReminderHelper.reminderStatus = newValue as Boolean
            }
            true
        }
        fun bindPreferenceSummaryToValue(preference: Preference?){
            preference?.onPreferenceChangeListener = summaryListener
            summaryListener.onPreferenceChange(preference, PreferenceManager.getDefaultSharedPreferences(preference?.context)
                .getBoolean(preference?.key, true))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
            val reminder = findPreference<SwitchPreferenceCompat>("remind_me")
            reminder?.isChecked = ReminderHelper.reminderStatus
            bindPreferenceSummaryToValue(reminder)
        }
    }
}