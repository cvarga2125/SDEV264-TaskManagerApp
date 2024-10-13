package com.example.taskmanagerapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply the theme before calling super.onCreate
        ThemeUtils.applyTheme(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val etTaskName = findViewById<EditText>(R.id.etTaskName)
        val etTaskDescription = findViewById<EditText>(R.id.etTaskDescription)
        val btnSaveTask = findViewById<Button>(R.id.btnSaveTask)

        btnSaveTask.setOnClickListener {
            val taskName = etTaskName.text.toString()
            val taskDescription = etTaskDescription.text.toString()

            if (taskName.isNotEmpty() && taskDescription.isNotEmpty()) {
                // Pass the task data back to MainActivity
                val resultIntent = Intent()
                resultIntent.putExtra("taskName", taskName)
                resultIntent.putExtra("taskDescription", taskDescription)
                setResult(Activity.RESULT_OK, resultIntent)
                finish() // Close AddTaskActivity and return to MainActivity
            } else {
                // Show error if task name or description is empty
                Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
