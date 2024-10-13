package com.example.taskmanagerapp

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

class PreferencesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply the theme before calling super.onCreate
        ThemeUtils.applyTheme(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        // Display the fragment as the main content.
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_container, SettingsFragment())
            .commit()
    }

    class SettingsFragment : PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            // Load the preferences from an XML resource
            setPreferencesFromResource(R.xml.preferences, rootKey)

            // Find the theme preference and set a change listener
            val themePreference = findPreference<ListPreference>("theme")
            themePreference?.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { _, newValue ->
                // When the theme is changed, recreate the activity
                if (newValue is String) {
                    activity?.recreate()
                    true
                } else {
                    false
                }
            }
        }
    }
}
