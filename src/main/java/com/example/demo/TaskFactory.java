package com.example.demo;

import java.util.Date;


import java.util.Date;

public class TaskFactory {
    public static Task createTask(String taskType) {
        switch (taskType) {
            case "work":
                return new WorkTask();
            case "personal":
                return new PersonalTask();
            case "shopping":
                return new ShoppingTask();
            default:
                throw new IllegalArgumentException("Invalid task type: " + taskType);
        }
    }

    public static Task createTask(String taskId, String taskTitle, String taskDescription, String taskSpeciality, Date taskDueDate, String taskType) {
        switch (taskType) {
            case "work":
                WorkTask workTask = new WorkTask();
                workTask.setId(Integer.parseInt(taskId));
                workTask.setTitle(taskTitle);
                workTask.setDescription(taskDescription);
                workTask.setDueDate(taskDueDate);
                workTask.setSpeciality(taskSpeciality);
                return workTask;
            case "personal":
                PersonalTask personalTask = new PersonalTask();
                personalTask.setId(Integer.parseInt(taskId));
                personalTask.setTitle(taskTitle);
                personalTask.setDescription(taskDescription);
                personalTask.setDueDate(taskDueDate);
                personalTask.setSpeciality(taskSpeciality);
                return personalTask;
            case "shopping":
                ShoppingTask shoppingTask = new ShoppingTask();
                shoppingTask.setId(Integer.parseInt(taskId));
                shoppingTask.setTitle(taskTitle);
                shoppingTask.setDescription(taskDescription);
                shoppingTask.setDueDate(taskDueDate);
                shoppingTask.setSpeciality(taskSpeciality);
                return shoppingTask;
            default:
                throw new IllegalArgumentException("Invalid task type: " + taskType);
        }
    }
}

