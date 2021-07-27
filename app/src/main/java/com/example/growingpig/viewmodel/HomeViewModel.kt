package com.example.growingpig.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.growingpig.model.Task
import com.example.growingpig.model.repository.database.AppDatabase


class HomeViewModel(app: Application) : AndroidViewModel(app) {

    var allTasks: MutableLiveData<List<Task>> = MutableLiveData()


    init {
        getAllTasks()
    }

    fun getAllTasksObserver(): MutableLiveData<List<Task>> {
        return allTasks
    }

    fun getAllTasks() {
        val taskDao = AppDatabase.getAppDatabase((getApplication())).taskDao()
        val list = taskDao.getAllTasks()

        allTasks.postValue(list)
    }

    fun createTask(task: Task) {
        val taskDao = AppDatabase.getAppDatabase(getApplication()).taskDao()
        taskDao.createTask(task)
        getAllTasks()
    }

    fun updateTask(task: Task) {
        val taskDao = AppDatabase.getAppDatabase(getApplication()).taskDao()
        taskDao.updateTask(task)
        getAllTasks()
    }

    fun deleteTask(task: Task) {
        val taskDao = AppDatabase.getAppDatabase(getApplication()).taskDao()
        taskDao.deleteTask(task)
        getAllTasks()

    }

    fun deleteAllTasks() {
        val taskDao = AppDatabase.getAppDatabase(getApplication()).taskDao()
        taskDao.deleteAllTasks()
        getAllTasks()
    }


}