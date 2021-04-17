package com.example.growingpig.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
class Task(title: String, priority: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var title: String = title
    var priority: String = priority
    var completed = false

}