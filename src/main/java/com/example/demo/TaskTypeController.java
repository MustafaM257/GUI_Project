package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


import java.net.URL;
import java.util.ResourceBundle;

public class TaskTypeController implements Initializable {
    @FXML
    public Button button_back;
    @FXML
    public Button button_worktask;
    @FXML
    public Button button_shoppingtask;
    @FXML
    public Button button_personaltask;
    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_back.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                DB.changeScene(event,"LoggedIn.fxml","Welcome!",null);


            }
        });
        button_shoppingtask.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
//                Redirect(event , "ShoppingTaskView.fxml");
                DB.changeScene(event,"ShoppingTaskView.fxml","Shopping Task",null);

            }
        });
        button_personaltask.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                DB.changeScene(event,"PersonalTaskView.fxml","Personal Task",null);



            }
        });
        button_worktask.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                DB.changeScene(event,"WorkTaskView.fxml","Work Task",null);
            }
        });
    }
    }
//    methods to change the view



