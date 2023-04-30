package com.example.demo;

import java.util.Date;

public class TaskFactory {
    public static Task createTask(String taskType) {
        return switch (taskType) {
            case "work" -> new WorkTask();
            case "personal" -> new PersonalTask();
            case "shopping" -> new ShoppingTask();
            default -> throw new IllegalArgumentException("Invalid task type: " + taskType);
        };
    }
    public static Task createTask(String taskId , String taskTitle , String taskDescription , String taskSpeciality, Date taskDueDate,String taskType) {
        return switch (taskType) {
            case "work" -> new WorkTask(taskId, taskTitle, taskDueDate);
            case "personal" -> new PersonalTask(taskId, taskTitle, taskDueDate);
            case "shopping" -> new ShoppingTask(taskId,taskTitle,taskDueDate);
            default -> throw new IllegalArgumentException("Invalid task type: " + taskType);
        };
    }
}
