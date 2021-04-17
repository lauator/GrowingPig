package com.example.growingpig.view.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.growingpig.model.Task

@Database(entities = arrayOf(Task::class), version = 1)
abstract class TaskDB: RoomDatabase() {

    abstract fun taskDAO(): TaskDAO

    companion object{
        private lateinit var context: Context
        private val database: TaskDB  by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Room.databaseBuilder(context, TaskDB::class.java, "task.db")
                .allowMainThreadQueries()
                .build()
        }

        fun getDatabase(context: Context): TaskDB{
            Companion.context = context.applicationContext
            return database
        }

        }
    }


}