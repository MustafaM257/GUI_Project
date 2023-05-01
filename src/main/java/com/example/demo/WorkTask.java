package com.example.demo;

import java.util.Date;

public class WorkTask implements Task {
    private int id;
    private String title;
    private String description;
    private String type;
    private String speciality;
    private Date dueDate;
    public WorkTask(){
        this.type = "work";
    }

    public WorkTask(int taskId, String taskTitle, String taskDescription, String taskSpeciality,Date taskDueDate) {
        this.id = taskId;
        this.title = taskTitle;
        this.description = taskDescription;
        this.type = "work";
        this.speciality = taskSpeciality;
        this.dueDate = taskDueDate;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int taskId) {
        this.id = taskId;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String taskTitle) {
        this.title = taskTitle;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String taskDescription) {
        this.description = taskDescription;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String taskType) {
        this.type = taskType;
    }

    @Override
    public String getSpeciality() {
        return speciality;
    }

    @Override
    public void setSpeciality(String taskSpeciality) {
        this.speciality = taskSpeciality;
    }

    @Override
    public Date getDueDate() {
        return dueDate;
    }

    @Override
    public void setDueDate(Date taskDueDate) {
        this.dueDate = taskDueDate;
    }

}
