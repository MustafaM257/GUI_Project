package com.example.demo;

import java.time.LocalDate;
import java.util.Date;

public class PersonalTask extends Task{

    public PersonalTask(){}
    protected PersonalTask(String title, String description, Date dueDate) {
        super(title, description, dueDate);
        this.speciality = "Personal";
    }


}
