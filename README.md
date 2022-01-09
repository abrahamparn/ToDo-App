# Task List
Made by:
- Julius Martin Hutagaol (ID: 001202000037)
- Abraham Naiborhu (ID: 001202000052)
- Yoel Armando (ID: 001202000106)
- Muhammad Imran (ID: 001202000010)
- Muhammad Daffa Iuliano Batubara (ID: 001202000078)

## Table of Content
- [Project Overview](#project-overview)
	- [User Manual](#user-manual)
- [Features & Code Walkthrough](#features)
- [Directory Structure](#directory-structure)

## Project Overview
This is a simple to do list app for Wireless and Mobile Programming final project. the final project's guide lines are as follow:
- Create a simple mobile application
- This mobile app should be an app that can be used and useful for daily needs/activities
- Your app should not be too complex, even if it is only consist of 1 main activity, it's OK
- Nex week(15th meet) you just need to present the concept (not the real app yet), via gmeet session (if available)
- Please submit the app, project files and documentation no latter than 31st of December 2021, 23:59 via link in ecampus (link/access will be given on the Final Exam week).
<p>Based on the criteria we decided to create a simple To Do Task using Room database as its depedency that allows users to add a todo. This simple ToDo app is akin to notes app where you can add ToDo, edit ToDo, and Delete ToDo. This app is also able to differentiate three type of task as in red for important, yellow for usual Task, and Green for simple task. This app is also able to rotate </p>

### User Manual

<p>Here is the screen shot of our Project. For the first time you download our app, it will show a "hi" Message with zero ToDo.</p>
<img src="https://user-images.githubusercontent.com/87258755/147503491-a2c92f1b-4762-4324-aebb-49de9b7ebe6b.png" height="400px"/>

<p>If you want to add a ToDo, please press the red plus button on the bottom of the screen. After you press that button, it will direct you to a new activity where you could add your ToDo. Notice that there are three type of color in this section. Red means urgent matter, Yellow means main task, and Greed means Side Task.</p>
<img src="https://user-images.githubusercontent.com/87258755/147503976-85391b49-7171-44e6-b2de-5bdde476468a.png" height="400px"/>

<p>After you have add your ToDo, the ToDo will be displayed in the frist activity that was shown when you open the app for the first time.</p>
<img src="https://user-images.githubusercontent.com/87258755/147503613-4216b7c1-b9b1-4acf-a45d-e3f64f7606da.png" height="400px"/>

<p>If you want to edit your ToDo or you want to delete the ToDo, long press one of the ToDo and there will be a pop up regarding of the things that you want to do (Delete or Edit).</p>
<img src="https://user-images.githubusercontent.com/87258755/147504096-100f17a4-0333-4592-bbc8-a2f1d810cbad.png" height="400px"/>


## Features
- Able to rotate
- Simple Design
- Create Database for Task
```
// TaskData class
package com.example.todolist.db


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
class TaskData(
    @ColumnInfo(name = "task_title")
    var title:String = "",
    @ColumnInfo(name = "task_priority")
    var priority: Int = 0,
    @ColumnInfo(name = "task_time")
    var timeTodo: String = "",
    @PrimaryKey(autoGenerate = true) var taskId: Int = 0){
    var detail: String = ""
}


```
```
// TaskDao
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
```
- Add Task
```
/// Add Task 
package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.db.TaskListDatabase
import kotlinx.android.synthetic.main.add_task_activity.*
import android.widget.RadioGroup
import android.widget.TimePicker
import com.example.todolist.db.TaskData

class AddTaskActivity : AppCompatActivity() , RadioGroup.OnCheckedChangeListener{


    private lateinit var taskAdapter: TaskAdapter
    private var taskDatabase: TaskListDatabase? = null
    private var priority = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_task_activity)
        taskAdapter = TaskAdapter(mutableListOf())


        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        taskDatabase = TaskListDatabase.getInstance(this)
        radioGroup.setOnCheckedChangeListener(this)

        val addTaskButton = findViewById<Button>(R.id.btnDoneTask)


        // Time Picker

        var timeText = ""
        val timePicker = findViewById<TimePicker>(R.id.timePicker)
        timePicker.setOnTimeChangedListener { _, hour, minute -> var hour = hour
            var am_pm = ""
            // AM_PM decider logic
            when {hour == 0 -> { hour += 12
                am_pm = "AM"
            }
                hour == 12 -> am_pm = "PM"
                hour > 12 -> { hour -= 12
                    am_pm = "PM"
                }
                else -> am_pm = "AM"
            }
            if (timeText == "") {
                val hours = if (hour < 10) "0" + hour else hour
                val min = if (minute < 10) "0" + minute else minute
                // display format of time
                val msg = "$hours : $min $am_pm"
                timeText = msg
            }
        }


        val title = intent.getStringExtra("title")
        val detail = intent.getStringExtra("detail")
        if (title == null || title == ""){
            btnDoneTask.setOnClickListener{
                val task = TaskData(title_ed.text.toString(), priority)
                task.detail = detail_ed.text.toString()
                task.timeTodo = timeText
                taskDatabase!!.getTaskDao().saveTask(task)
                finish()
            }
        }else{
            btnDoneTask.text = getString(R.string.update)
            val tId = intent.getIntExtra("tId", 0)
            title_ed.setText(title)
            detail_ed.setText(detail)
            btnDoneTask.setOnClickListener {
                val task = TaskData(title_ed.text.toString(), priority, timeTodo = timeText, tId)
                task.detail = detail_ed.text.toString()
                taskDatabase!!.getTaskDao().updateTask(task)
                finish()
            }
        }
    }



    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        if (checkedId == R.id.medium){
            priority = 2
        }else if (checkedId == R.id.high) {
            priority = 3
        }
    }

//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        if (item?.itemId == android.R.id.home){
//            startActivity(Intent(this, MainActivity::class.java))
//        }
//        return super.onOptionsItemSelected(item!!)
//    }



}

```
- Edit Task
```
	// MainActivity class
	override fun onTaskItemLongClicked(todo: TaskData) {
        val alertDialog = AlertDialog.Builder(this)
            .setItems(R.array.dialog_list, DialogInterface.OnClickListener { dialog, which ->
                if (which==0){
                    val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
                    intent.putExtra("tId", todo.taskId)
                    intent.putExtra("title", todo.title)
                    intent.putExtra("time", todo.timeTodo)
                    intent.putExtra("priority", todo.priority)
                    intent.putExtra("detail", todo.detail)
                    startActivity(intent)
                }else{
                    taskDatabase?.getTaskDao()?.removeTask(todo)
                    onResume()
                }
                dialog.dismiss()
            })
            .create()
        alertDialog.show()
	
	
    }
```
	
```
// AddTaskActivity
val title = intent.getStringExtra("title")
        val detail = intent.getStringExtra("detail")
        if (title == null || title == ""){
            btnDoneTask.setOnClickListener{
                val task = TaskData(title_ed.text.toString(), priority)
                task.detail = detail_ed.text.toString()
                task.timeTodo = timeText
                taskDatabase!!.getTaskDao().saveTask(task)
                finish()
            }
        }else{
            btnDoneTask.text = getString(R.string.update)
            val tId = intent.getIntExtra("tId", 0)
            title_ed.setText(title)
            detail_ed.setText(detail)
            btnDoneTask.setOnClickListener {
                val task = TaskData(title_ed.text.toString(), priority, timeTodo = timeText, tId)
                task.detail = detail_ed.text.toString()
                taskDatabase!!.getTaskDao().updateTask(task)
                finish()
            }
        }
```
- Delete Task
```
// MainActivity class
	override fun onTaskItemLongClicked(todo: TaskData) {
        val alertDialog = AlertDialog.Builder(this)
            .setItems(R.array.dialog_list, DialogInterface.OnClickListener { dialog, which ->
                if (which==0){
                    val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
                    intent.putExtra("tId", todo.taskId)
                    intent.putExtra("title", todo.title)
                    intent.putExtra("time", todo.timeTodo)
                    intent.putExtra("priority", todo.priority)
                    intent.putExtra("detail", todo.detail)
                    startActivity(intent)
                }else{
                    taskDatabase?.getTaskDao()?.removeTask(todo) 
                    onResume()
                }
                dialog.dismiss()
            })
            .create()
        alertDialog.show()
```
  
## Directory Structure
The following is a high level overview of relevant files and folders.
```
TaskList
    |____ App
    |      |____ src
    |             |____ androidTest
    |             |         |____java
    |             |               |____com.example.todolist
    |             |                           |____ ExampleInstrumentedTest.kt
    |             |____ Main
    |_____ gradle |       |____ Java
                  |       |       |____com.example.todolist
                  |       |                       |____db
                  |       |                       |    |____TaskDao.kt
                  |       |                       |    |____TaskData.kt
                  |       |                       |    |____TaskDatabase.kt
                  |       |                       |
                  |       |                       |____AddTaskActivity.kt
                  |       |                       |____MainActivity.kt
                  |       |                       |____TaskAdapter.kt
                  |       |                       |____TaskAdapterNeo.kt
                  |       |                       |____Todo.kt
                  |       |
                  |       |
                  |       |____res
                  |       |     |____drawable
                  |       |     |         |____...
                  |       |     |____drawable-v24
                  |       |     |         |____....
                  |       |     |____layout
                  |       |     |         |____....
                  |       |     |____mipmap-anydpi-v26
                  |       |     |         |____....
                  |       |     |____mipmap-hdpi
                  |       |     |         |____....
                  |       |     |____mipmap-mdpi
                  |       |     |         |____....
                  |       |     |____mipmap-xhdpi
                  |       |     |         |____....
                  |       |     |____mipmap-xxhdpi
                  |       |     |         |____....
                  |       |     |____mipmap_xxxhdpi
                  |       |     |         |____....
                  |       |     |____values
                  |       |              |____....
                  |       |     
                  |       |     
                  |       |____AndroidManifest.xml     
                  |             
                  |             
                  |___test
                        |___com.example.todolist
                                       |____ ExampleUnitTest.kt
                  
```
## Thank you
Thank you for being a great lecture sir!<br>
This project is not much, we hope you like it!<br>
Happy new year sir!
