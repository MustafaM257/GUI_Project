package com.example.demo;

import java.time.LocalDate;
import java.util.Date;

public class PersonalTask extends Task{

    private String priority;
    public PersonalTask(){}
    protected PersonalTask(String title, String description, Date dueDate, String priority) {
        super(title, description, dueDate);
        this.priority = priority;
    }
    public void setPriority(String priority) {this.priority=priority;}
    public String getStore() {return this.priority;}

    @Override
    public String toString() {

        return super.toString()+
                "priority='" + priority + '\'' +
                '}';
    }
}
