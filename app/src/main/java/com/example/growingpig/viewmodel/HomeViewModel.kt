package com.example.growingpig.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.growingpig.model.Task
import com.example.growingpig.model.repository.database.AppDatabase


class HomeViewModel(app: Application) : AndroidViewModel(app) {

    var allTasks: MutableLiveData<List<Task>> = MutableLiveData()
    val taskDao = AppDatabase.getAppDatabase((getApplication())).taskDao()

    init {
        getAllTasks()
    }

    fun getAllTasksObserver(): MutableLiveData<List<Task>> {
        return allTasks
    }

    fun getAllTasks() {

        val list = taskDao.getAllTasks()

        allTasks.postValue(list)
    }

    fun createTask(task: Task) {

        taskDao.createTask(task)
        getAllTasks()
    }

    fun updateTask(task: Task) {

        taskDao.updateTask(task)
        getAllTasks()
    }

    fun deleteTask(task: Task) {

        taskDao.deleteTask(task)
        getAllTasks()

    }

    fun deleteAllTasks() {

        taskDao.deleteAllTasks()
        getAllTasks()
    }


}