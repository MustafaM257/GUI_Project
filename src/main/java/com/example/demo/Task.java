package com.example.demo;

import java.time.LocalDate;

public abstract class Task {
    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    protected Task(){};

    protected Task(String title, String description, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }
//    Getters & Setters
    public String getTitle(){return this.title;}
    public String getDescription(){return this.description;};
    public LocalDate getDueDate(){return this.dueDate;}

    public void setTitle(String title) {this.title = title;}
    public void setDescription(String description){this.description=description;}
    public void setDueDate(LocalDate dueDate) {this.dueDate = dueDate;}

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