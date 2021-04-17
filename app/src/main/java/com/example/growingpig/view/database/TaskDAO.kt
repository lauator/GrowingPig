package com.example.growingpig.view.database

import androidx.room.*
import com.example.growingpig.model.Task

@Dao
interface TaskDAO {

    @Insert
    fun createTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): List<Task>

    @Query("DELETE FROM tasks")
    fun deleteAllTasks()

    @Delete
    fun deleteTask(task: Task)

}