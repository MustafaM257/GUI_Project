package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class PersonalTaskController implements Initializable {
    @FXML
    public Button button_createtask;
    @FXML
    public Button button_back;
    @FXML
    public TextField name;
    @FXML
    public TextField description;
    @FXML
    public TextField priority;

    @FXML
    private DatePicker datePicker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AtomicReference<LocalDate> selectedDate = new AtomicReference<>();
        button_createtask.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (name.getText().isEmpty() || description.getText().isEmpty() || priority.getText().isEmpty() || datePicker.getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Invalid input");
                    if(name.getText().isEmpty()){
                        alert.setContentText("Please specify the task's name");
                    }
                    else if(description.getText().isEmpty()){
                        alert.setContentText("Please specify the task's description");
                    }
                    else if(datePicker.getValue() == null){
                        alert.setContentText("Please specify the task's due date");
                    }
                    else if(priority.getText().isEmpty()){
                        alert.setContentText("Please specify the project name of your task");
                    }
                    alert.showAndWait();
                    return;
                } else {
                    // Call the addTaskToDatabase() function if all fields have a value
//                        addTaskToDatabase();
                    System.out.println("Selected date: " + selectedDate.get());
                    System.out.println("name: " + name.getText());
                }
            }
        });

        datePicker.setOnAction(event -> {
            // Get the selected date
            selectedDate.set(datePicker.getValue());
        });
        button_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskType.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
