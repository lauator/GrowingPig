package com.example.growingpig.view.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.growingpig.model.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun taskDAO(): TaskDAO

   /* companion object{


        private lateinit var context: Context

        private val dB: AppDatabase  by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "task.db")
                .allowMainThreadQueries()
                .build()
        }

        fun getDatabase(context: Context): AppDatabase{
            Companion.context = context.applicationContext
            return dB
        }

        }

    */

    }


