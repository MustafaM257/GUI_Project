package com.example.demo;

import java.time.LocalDate;
import java.util.Date;

public abstract class Task {
    private int id;
    private String title;
    private String description;
    private Date dueDate;
    protected String speciality;
    protected Task(){};

    protected Task(String title, String description, Date dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }
//    Getters & Setters
    public String getTitle(){return this.title;}
    public String getDescription(){return this.description;};
    public Date getDueDate(){return this.dueDate;}

    public void setTitle(String title) {this.title = title;}
    public void setDescription(String description){this.description=description;}
    public void setDueDate(Date dueDate) {this.dueDate = dueDate;}

    public int getId() {return this.id;}
    public void setId(int id){this.id=id;}

    @Override
    public String toString() {
        return "Task:\n" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                '\n';
    }
}