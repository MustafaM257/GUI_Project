package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {
    // We're using Initializable for controller and we have to override initialize
    // initialize() is called after all @FXML annotated members have been injected

    @FXML
    private Button button_logout;
    @FXML
    private Label label_welcome;
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DB.changeScene(actionEvent,"Index.fxml","Log in!",null);
            }
        });
    }
    public void setUserInformation(String username) {
        label_welcome.setText("Welcome "+ username + " !");
    }
}
