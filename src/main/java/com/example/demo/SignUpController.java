package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private Button button_signup;
    @FXML
    private Button button_login;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()) {
                    DB.signUpUser(event,tf_username.getText(),tf_password.getText());
                }
                else {
                    System.out.println("Please fill in all the info");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all informatino to sign up!");
                    alert.show();
                }
            }
        });
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DB.changeScene(event,"sample.fxml","Log in !",null);
            }
        });

    }
}
