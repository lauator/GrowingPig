package com.example.growingpig.view.database

import android.app.Application
import androidx.room.Room


class TaskApp : Application() {

    val room =
        Room
        .databaseBuilder(this, AppDatabase::class.java, "tasks")
        .build()

}