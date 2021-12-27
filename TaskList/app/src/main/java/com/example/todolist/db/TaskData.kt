package com.example.todolist.db


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
class TaskData(
    @ColumnInfo(name = "task_title") var title:String = "",
    @ColumnInfo(name = "task_priority") var priority: Int = 0,
    @PrimaryKey(autoGenerate = true) var taskId: Int = 0
) {
    var detail: String = ""
}

