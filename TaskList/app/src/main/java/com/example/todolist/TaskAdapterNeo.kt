package com.example.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.db.TaskData

class TaskAdapterNeo(var taskList: List<TaskData>? = ArrayList<TaskData>()): RecyclerView.Adapter<TaskAdapterNeo.TodoViewHolder>(){

    private var onTodoItemClickedListener: OnTodoItemClickedListener?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val layout = if (itemCount == 0) R.layout.empty_view else R.layout.task_item
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return TodoViewHolder(view, taskList!!)
    }

    override fun getItemCount(): Int {
        return if(taskList!!.isEmpty()) 0 else taskList!!.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int){
        holder.view.setOnClickListener{onTodoItemClickedListener!!.onTaskItemClicked(taskList!!.get(position))}
        holder.view.setOnLongClickListener{
            onTodoItemClickedListener!!.onTaskItemLongClicked(taskList!!.get(position))
            true
        }
        holder.onBindViews(position)
    }

    inner class TodoViewHolder(val view: View, val taskList: List<TaskData>): RecyclerView.ViewHolder(view){
        fun onBindViews(position: Int){
            if (itemCount != 0){
                view.findViewById<TextView>(R.id.title).text = taskList.get(position).title
                view.findViewById<TextView>(R.id.first_letter).text = taskList.get(position).title.first().toUpperCase().toString()
                view.findViewById<ImageView>(R.id.priority_imgView).setImageResource(getImage(taskList.get(position).priority))
            }

        }
        private fun getImage(priority: Int): Int
                = if (priority == 1) R.drawable.low_priority else if(priority == 2) R.drawable.medium_priority else R.drawable.high_priority
    }

    fun setTaskItemClickedListener(onTaskItemClickedListener: MainActivity){
        this.onTodoItemClickedListener = onTaskItemClickedListener
    }

    interface OnTodoItemClickedListener{
        fun onTaskItemClicked(todo: TaskData)
        fun onTaskItemLongClicked(todo: TaskData)
    }
}