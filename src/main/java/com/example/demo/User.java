package com.example.demo;

import java.util.ArrayList;

public class User {
    private static User instance = null;
    private ArrayList<Task> tasks = null;
    private String username;
    private int userid;

    private User() {
    }

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setTasks(ArrayList<Task> tasks ) { this.tasks=tasks;}
    public int getUserid(){return this.userid;}
    public void setUserid(int userid) {this.userid = userid;}
    public ArrayList<Task> getTasks(){return this.tasks;}
}
