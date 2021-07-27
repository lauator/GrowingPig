package com.example.growingpig.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.growingpig.R
import com.example.growingpig.model.Task

class TaskAdapter(val taskListener: TaskListener) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    var tasks = ArrayList<Task>()

    fun setListData(data: ArrayList<Task>) {
        this.tasks = data
    }


    inner class TaskViewHolder(itemView: View, val listener: TaskListener) :
        RecyclerView.ViewHolder(itemView) {

        val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        val tvPriority = itemView.findViewById<TextView>(R.id.tv_priority)
        val cbTask = itemView.findViewById<CheckBox>(R.id.cb_task)

        fun bind(data: Task) {
            tvTitle.text = data.title
            tvPriority.text = data.priority

            cbTask.setOnClickListener() {
                listener.onDeleteTaskClickListener(data)
            }
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(layoutInflater, taskListener)
    }


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        holder.bind(tasks[position])


    }

    override fun getItemCount() = tasks.size


}