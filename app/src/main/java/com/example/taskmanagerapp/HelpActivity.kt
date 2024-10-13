package com.example.taskmanagerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply the theme before calling super.onCreate
        ThemeUtils.applyTheme(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
    }
}
