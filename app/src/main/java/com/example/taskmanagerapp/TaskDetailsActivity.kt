package com.example.taskmanagerapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TaskDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply the theme before calling super.onCreate
        ThemeUtils.applyTheme(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_details)

        val taskNameTextView = findViewById<TextView>(R.id.tvTaskDetailsName)
        val taskDescriptionTextView = findViewById<TextView>(R.id.tvTaskDetailsDescription)

        // Retrieve the task details from the intent
        val taskName = intent.getStringExtra("taskName")
        val taskDescription = intent.getStringExtra("taskDescription")

        // Display the task details
        taskName?.let { taskNameTextView.text = it }
        taskDescription?.let { taskDescriptionTextView.text = it }
    }
}
