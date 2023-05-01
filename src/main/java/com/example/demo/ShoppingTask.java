package com.example.demo;

import java.time.LocalDate;
import java.util.Date;
import java.util.Date;

public class ShoppingTask implements Task {
    private int id;
    private String title;
    private String description;
    private String type;
    protected String speciality;
    private Date dueDate;
    public ShoppingTask(){
        this.type ="shopping";
    }
    public ShoppingTask(int id, String title, String description,String taskSpeciality, Date dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = "shopping";
        this.speciality = taskSpeciality;
        this.dueDate = dueDate;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String taskType) {
        this.type=taskType;
    }

    @Override
    public Date getDueDate() {
        return this.dueDate;
    }

    @Override
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String getSpeciality() {
        return this.speciality;
    }

    @Override
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "PersonalTask{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", speciality='" + speciality + '\'' +
                ", dueDate=" + dueDate +
                '}';
    }
}
