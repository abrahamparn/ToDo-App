package com.example.todolist.db

import androidx.room.*


@Dao
interface TaskDao {
    /**
     * SELECT -> This retrieve rows from a table in a database
     * FROM -> You specify the table to retrieve the rows from
     * ORDER BY -> This is just a sort algorithm
     * ASC -> Ascending order
     * WHERE -> This is a condition used to query data
     * */
    @Query("SELECT*FROM task ORDER BY taskId ASC")
    fun getTaskList(): List<TaskData>


    @Query("SELECT*FROM task WHERE taskId=:tid")
    fun getTaskItem(tid: Int): TaskData
    /**
     * @param todo is what we want to save in our database
     * so many conflict can occur when a data is to be saved, the strategy is used to handle such conflicts
     * Abort -> this aborts the transaction
     * Ignore -> this ignores and continues the transaction
     * Replace -> this replace the data
     * others includes fail, and roolback
     * */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveTask(todo: TaskData)

    @Update
    fun updateTask(todo: TaskData)

    @Delete
    fun removeTask(todo: TaskData)
}
