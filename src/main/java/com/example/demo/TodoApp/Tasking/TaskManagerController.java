package com.example.demo.TodoApp.Tasking;

import java.util.List;

public class TaskManagerController {
    private List<Task> tasks;

    // constructor to initialize the list of tasks

    // methods for managing the tasks
    public void addTask(Task task) {
        tasks.add(task);
        task.saveToDatabase();
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
        task.deleteFromDatabase();
    }

    public void updateTask(Task task) {
        task.updateInDatabase();
    }

    public List<Task> getAllTasks() {
        // implementation to retrieve all tasks from the database
        return tasks;
    }

    // other methods as needed
}

