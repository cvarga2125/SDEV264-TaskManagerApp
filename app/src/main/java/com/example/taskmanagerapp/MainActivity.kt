package com.example.taskmanagerapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter
    private val tasks = mutableListOf<Task>()  // List to store tasks

    // Store the current theme to detect changes
    private var currentTheme: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply the theme before calling super.onCreate
        ThemeUtils.applyTheme(this)
        currentTheme = getCurrentTheme() // Save the current theme

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvTasks = findViewById<RecyclerView>(R.id.rvTasks)
        val fabAddTask = findViewById<FloatingActionButton>(R.id.fabAddTask)
        val btnPreferences = findViewById<Button>(R.id.btnPreferences)
        val btnHelp = findViewById<Button>(R.id.btnHelp)

        // Set up the RecyclerView with the adapter and handle click events
        taskAdapter = TaskAdapter(tasks) { task ->
            // When a task is clicked, open TaskDetailsActivity
            val intent = Intent(this, TaskDetailsActivity::class.java).apply {
                putExtra("taskName", task.name)
                putExtra("taskDescription", task.description)
            }
            startActivity(intent)
        }
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = taskAdapter

        fabAddTask.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_TASK)
        }

        btnPreferences.setOnClickListener {
            val intent = Intent(this, PreferencesActivity::class.java)
            startActivity(intent)
        }

        btnHelp.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }
    }

    // Get the current theme from SharedPreferences
    private fun getCurrentTheme(): String? {
        val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        return sharedPreferences.getString("theme", "light")
    }

    // Check for theme change in onResume and restart activity if necessary
    override fun onResume() {
        super.onResume()
        val newTheme = getCurrentTheme()
        if (newTheme != currentTheme) {
            recreate() // Restart the activity to apply the new theme
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_ADD_TASK && resultCode == RESULT_OK) {
            val taskName = data?.getStringExtra("taskName")
            val taskDescription = data?.getStringExtra("taskDescription")

            if (taskName != null && taskDescription != null) {
                val newTask = Task(taskName, taskDescription)
                tasks.add(newTask)
                taskAdapter.notifyDataSetChanged()
            }
        }
    }

    companion object {
        const val REQUEST_CODE_ADD_TASK = 100
    }
}
