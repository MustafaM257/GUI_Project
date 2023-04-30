package com.example.demo;

import java.time.LocalDate;
import java.util.Date;

public class WorkTask extends Task {

    private String project;
    public WorkTask(){};

    protected WorkTask(String title, String description, Date dueDate, String project) {
        super(title, description, dueDate);
        this.project = project;
    }
    public void setProject(String project) {this.project=project;}
    public String getProject() {return this.project;}
    @Override
    public String toString() {

        return super.toString()+
                "project='" + project + '\'' +
                '}';
    }
}
