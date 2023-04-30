package com.example.demo;

import java.time.LocalDate;
import java.util.Date;

public class ShoppingTask extends Task{
    public ShoppingTask(){};
    protected ShoppingTask(String title, String description, Date dueDate) {
        super(title, description, dueDate);
        this.speciality = "Shopping";
    }
}
