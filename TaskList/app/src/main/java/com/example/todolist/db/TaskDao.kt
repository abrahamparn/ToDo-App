package com.example.todolist.db

import androidx.room.*


@Dao
interface TaskDao {

    @Query("SELECT*FROM task ORDER BY taskId ASC")
    fun getTaskList(): List<TaskData>

    @Query("SELECT*FROM task WHERE taskId=:tid")
    fun getTaskItem(tid: Int): TaskData

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveTask(todo: TaskData)

    @Update
    fun updateTask(todo: TaskData)

    @Delete
    fun removeTask(todo: TaskData)
}
