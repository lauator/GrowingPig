package com.example.growingpig.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.growingpig.R
import com.example.growingpig.model.Task

class TaskAdapter(private val tasks: ArrayList<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {



    class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
       // val cbTask = itemView.findViewById<CheckBox>(R.id.cb_keepConnect)
        val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        val tvPriority = itemView.findViewById<TextView>(R.id.tv_priority)

        fun bindTask(task: Task) {
            tvTitle.text = task.title
            tvPriority.text = task.priority
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val itemTask = tasks[position]
        holder.bindTask(itemTask)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

}