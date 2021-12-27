# Task List
Made by:
- Julius Martin Hutagaol
- Abraham Naiborhu
- Yoel Armando
- Muhammad Imran
- Muhammad Dafa

## Table of Content
- [Project Overview](#project-overview)
- [Features](#features)
- [Directory Structure](#directory-structure)

## Project Overview
This is a simple to do list app for Wireless and Mobile Programming final project. the final project's guide lines are as follow:
- Create a simple mobil application
- This mobile app should be an app that can be used and useful for daily needs/activities
- Your app should not be too complex, even if it is only consist of 1 main activity, it's OK
- Nex week(15th meet) you just need to present the concept (not the real app yet), via gmeet session (if available)
- Please submit the app, project files and documentation no latter than 31st of December 2021, 23:59 via link in ecampus (link/access will be given on the Final Exam week).
<p>Based on the criteria we decided to create a simple To Do Task using Room database as its depedency that allows users to add a todo.</p>
<p>Here is the screen shot of our Project</p>

- This is the First Page of the app (left: with no task, right: with some tasks)

<img src="https://user-images.githubusercontent.com/87258755/147503491-a2c92f1b-4762-4324-aebb-49de9b7ebe6b.png" height="400px"/> <img src="https://user-images.githubusercontent.com/87258755/147503613-4216b7c1-b9b1-4acf-a45d-e3f64f7606da.png" height="400px"/>

- This is the add task section
<img src="https://user-images.githubusercontent.com/87258755/147503976-85391b49-7171-44e6-b2de-5bdde476468a.png" height="400px"/>
<p>Notice that there are three type of color in this section. Red means urgent matter, Yellow means main task, and Greed means Side Task.</p>


- This is the pop up if you press any of the task for some times.
<img src="https://user-images.githubusercontent.com/87258755/147504096-100f17a4-0333-4592-bbc8-a2f1d810cbad.png" height="400px"/>


## Features
- Able to rotate
- Simple Design
- Add Task
- Edit Task
- Delete Task
  
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
Thank you for being a great lecture sir!
This project is not much, we hope you like it!
Happy new year sir!
