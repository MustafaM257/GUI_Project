package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class DB {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        if(username != null){
            User user = User.getInstance();
            user.setUsername(username);
        }
        Parent root = null;
            try
            {
                root = FXMLLoader.load(Objects.requireNonNull(DB.class.getResource(fxmlFile)));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist", "root", "");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("user already exists");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users (username,password) VALUES (?,?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.executeUpdate();
                //change scene
                changeScene(event, "LoggedIn.fxml", "Welcome: ", username);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void logInUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist", "root", "");
            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found in data base");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided username or password is incorrect");
                alert.show();
            } else {
                while(resultSet.next())
                {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                        changeScene(event, "LoggedIn.fxml", "Welcome!", username);
                    } else {
                        System.out.println("Passwords don't match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided username or password is incorrect");
                        alert.show();
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if(resultSet != null){
                try{
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement!= null){
                try{
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!= null){
                try{
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // now add ,remove , or delete tasks from database
    public static boolean addTaskToDatabase(){
        return false;
    }
    public static boolean removeTaskFromDatabase(int taskId){
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist", "root", "");
            stmt = connection.prepareStatement("DELETE FROM tasks WHERE id = ?");
            stmt.setInt(1, taskId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean modifyTaskFromDatabase(int taskId,String title , String description , Date dueDate){
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist", "root", "");
             stmt = connection.prepareStatement("UPDATE tasks SET title = ?, description = ?, dueDate = ? WHERE id = ?");
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setDate(3, dueDate);
            stmt.setInt(4, taskId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public static Task readTaskFromDatabase(int taskId){
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist", "root", "");
             stmt = connection.prepareStatement("SELECT * FROM tasks WHERE id = ?");
            stmt.setInt(1, taskId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                LocalDate dueDate = rs.getDate("dueDate").toLocalDate();
//                return Task(taskId,title, description, dueDate);
//                Factory
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public static ArrayList<Task> readTasksFromDatabase(){
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist", "root", "");

            stmt = connection.prepareStatement("SELECT * FROM tasks");

            ResultSet rs = stmt.executeQuery();
            ArrayList<Task> tasks = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                LocalDate dueDate = rs.getDate("dueDate").toLocalDate();
//                tasks.add(new Task(id, title, description, dueDate));
//                Factory
            }

            return tasks;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
