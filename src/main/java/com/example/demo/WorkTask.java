package com.example.demo;

import java.time.LocalDate;
import java.util.Date;

public class WorkTask extends Task {

    private String project;
    public WorkTask(){};

    protected WorkTask(String title, String description, Date dueDate) {
        super(title, description, dueDate);
        this.speciality = "Work";
    }

}
