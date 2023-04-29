package com.example.demo;

import java.time.LocalDate;

public class ShoppingTask extends Task{
    private String store;
    public ShoppingTask(){};
    protected ShoppingTask(String title, String description, LocalDate dueDate, String store) {
        super(title, description, dueDate);
        this.store = store;
    }
    public void setStore(String store) {this.store = store;}
    public String getStore() {return this.store;}
    @Override
    public String toString() {

        return super.toString()+
                "store='" + store + '\'' +
                '}';
    }
}
