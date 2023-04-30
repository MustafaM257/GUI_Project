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
import java.util.List;
import java.util.Objects;

public class DB {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        if(username != null){
            User user = User.getInstance();
            user.setUsername(username);
            user.setUserid(DB.getUserId(username));
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
    public static boolean addTaskToDatabase(String taskTitle, String taskDescription, String taskSpeciality, Date taskDueDate, String taskType, int userId) {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist", "root", "");
            stmt = connection.prepareStatement("INSERT INTO tasks (taskTitle, taskDescription, taskSpeciality, taskDueDate, taskType, userid) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, taskTitle);
            stmt.setString(2, taskDescription);
            stmt.setString(3, taskSpeciality);
            stmt.setDate(4, taskDueDate);
            stmt.setString(5, taskType);
            stmt.setInt(6, userId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean removeTaskFromDatabase(int taskId){
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist", "root", "");
            stmt = connection.prepareStatement("DELETE FROM tasks WHERE taskId = ?");
            stmt.setInt(1, taskId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean updateTaskInDatabase(int taskId, String taskTitle, String taskDescription, String taskSpeciality, Date taskDueDate, String taskType, int userId) {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist", "root", "");
            stmt = connection.prepareStatement("UPDATE tasks SET taskTitle=?, taskDescription=?, taskSpeciality=?, taskDueDate=?, taskType=?, userId=? WHERE taskId=?");
            stmt.setString(1, taskTitle);
            stmt.setString(2, taskDescription);
            stmt.setString(3, taskSpeciality);
            stmt.setDate(4, taskDueDate);
            stmt.setString(5, taskType);
            stmt.setInt(6, userId);
            stmt.setInt(7, taskId);
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
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM tasks WHERE taskId = ?");
            stmt.setInt(1, taskId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Create a new task using the task factory
                Task task = TaskFactory.createTask(rs.getString("taskType"));

                // Set the task properties from the database result set
                task.setId(rs.getInt("taskId"));
                task.setTitle(rs.getString("taskTitle"));
                task.setDescription(rs.getString("taskDescription"));
                task.setDueDate(rs.getDate("taskDueDate"));
                task.speciality = "taskType";
                // Add the task to the list
                return task;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public static List<Task> getTasksForSpecificUser(int userId) {
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Task> tasks = new ArrayList<>();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist", "root", "");
            stmt = connection.prepareStatement("SELECT * FROM tasks WHERE userid = ?");
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                // Create a new task using the task factory
                Task task = TaskFactory.createTask(rs.getString("taskType"));

                // Set the task properties from the database result set
                task.setId(rs.getInt("taskId"));
                task.setTitle(rs.getString("taskTitle"));
                task.setDescription(rs.getString("taskDescription"));
                task.setDueDate(rs.getDate("taskDueDate"));
                task.speciality = "taskType";
                // Add the task to the list
                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tasks;
    }
    // Function to retrieve user ID from database based on username
    public static int getUserId(String username) {
        Connection connection = null;
        PreparedStatement stmt = null;
        int userId = 0;

        try {
            // Register JDBC driver
            // Open a connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist", "root", "");

            // Prepare statement to retrieve user ID
            String sql = "SELECT userid FROM users WHERE username=?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);

            // Execute query and retrieve result
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("userid");
            }
            // Clean-up environment
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ignored) {
            } // nothing we can do
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        return userId;
    }
}
