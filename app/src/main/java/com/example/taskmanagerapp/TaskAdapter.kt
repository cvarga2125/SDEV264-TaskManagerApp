package com.example.taskmanagerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val tasks: List<Task>, private val listener: (Task) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task, listener)  // Bind the task and listener
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskName: TextView = itemView.findViewById(R.id.tvTaskName)
        private val taskDescription: TextView = itemView.findViewById(R.id.tvTaskDescription)

        fun bind(task: Task, listener: (Task) -> Unit) {
            taskName.text = task.name
            taskDescription.text = task.description
            itemView.setOnClickListener { listener(task) }
        }
    }
}

