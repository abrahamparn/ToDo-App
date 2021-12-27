package com.example.todolist.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [TaskData::class], version = 1, exportSchema = false)
abstract class TaskListDatabase: RoomDatabase(){


      //This is an abstract method that returns a dao for the Db
    abstract fun getTaskDao(): TaskDao


    //A singleton design pattern is used to ensure that the database instance created is one
    companion object {
        private const val databaseName = "taskdatabase"
        private var taskListDatabase: TaskListDatabase? = null

        fun getInstance(context: Context): TaskListDatabase?{
            if (taskListDatabase == null){
                taskListDatabase = Room.databaseBuilder(context,
                    TaskListDatabase::class.java,
                    TaskListDatabase.databaseName)
                    .allowMainThreadQueries()
                    .build()
            }
            return taskListDatabase
        }
    }
}