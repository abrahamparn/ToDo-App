package com.example.todolist

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.db.TaskData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.todolist.db.TaskListDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    TaskAdapterNeo.OnTodoItemClickedListener {

    private var taskDatabase: TaskListDatabase? = null
    private var taskAdapter: TaskAdapterNeo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskDatabase = TaskListDatabase.getInstance(this)
        taskAdapter = TaskAdapterNeo(mutableListOf())
        taskAdapter?.setTaskItemClickedListener(this)

        val fabAddTask = findViewById<FloatingActionButton>(R.id.fabAddTask)





        fabAddTask.setOnClickListener{
            startActivity(Intent(this , AddTaskActivity::class.java))
        }


        /* Reworkkk*/
        /*btnAddTodo.setOnClickListener {
            val todoTitle = etTodoTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                taskAdapter.addTodo(todo)
                etTodoTitle.text.clear()
            }
        }

        btnDeleteDoneTodos.setOnClickListener {
            taskAdapter.deleteDoneTodos()
        }*/
    }

    override fun onResume() {
        super.onResume()
        taskAdapter?.taskList=taskDatabase?.getTaskDao()?.getTaskList()
        rvTaskList.adapter = taskAdapter
        rvTaskList.layoutManager = LinearLayoutManager(this)
        rvTaskList.hasFixedSize()
    }

    override fun onTaskItemClicked(todo: TaskData) {
        val intent = Intent(this, AddTaskActivity::class.java)
        intent.putExtra("tId", todo.taskId)
        intent.putExtra("title", todo.title)
        intent.putExtra("priority", todo.priority)
        intent.putExtra("detail", todo.detail)
        startActivity(intent)
    }

    override fun onTaskItemLongClicked(todo: TaskData) {
        val alertDialog = AlertDialog.Builder(this)
            .setItems(R.array.dialog_list, DialogInterface.OnClickListener { dialog, which ->
                if (which==0){
                    val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
                    intent.putExtra("tId", todo.taskId)
                    intent.putExtra("title", todo.title)
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
}