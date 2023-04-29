package com.example.demo;

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
}
