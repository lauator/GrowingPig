package com.example.growingpig.view.adapter

import com.example.growingpig.model.Task

interface TaskListener {
    fun onDeleteTaskClickListener(task: Task)

}