package com.example.demo;

import java.time.LocalDate;
import java.util.Date;

import java.util.Date;

public interface Task {
    int getId();
    void setId(int taskId);

    String getTitle();
    void setTitle(String taskTitle);

    String getDescription();
    void setDescription(String taskDescription);

    String getType();
    void setType(String taskType);

    String getSpeciality();
    void setSpeciality(String taskSpeciality);

    Date getDueDate();
    void setDueDate(Date taskDueDate);
}
