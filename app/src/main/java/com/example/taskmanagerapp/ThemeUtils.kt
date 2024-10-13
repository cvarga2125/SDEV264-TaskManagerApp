package com.example.taskmanagerapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager

object ThemeUtils {

    fun applyTheme(activity: AppCompatActivity) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        val themePreference = sharedPreferences.getString("theme", "light")
        if (themePreference == "dark") {
            activity.setTheme(R.style.AppTheme_Dark)
        } else {
            activity.setTheme(R.style.AppTheme)
        }
    }
}
