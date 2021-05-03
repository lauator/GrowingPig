package com.example.growingpig.view.database

import androidx.room.*
import com.example.growingpig.model.Task

@Dao
interface TaskDAO {

    @Insert
    suspend fun createTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<Task>

    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks()

    @Delete
    suspend  fun deleteTask(task: Task)

}