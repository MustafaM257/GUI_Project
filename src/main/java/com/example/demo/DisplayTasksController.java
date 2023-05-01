package com.example.demo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class DisplayTasksController implements Initializable {
    @FXML
    private TableView<Task> tableView;
    @FXML
    public Button button_back;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("LoggedIn.fxml"));
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
        DB  databaseHelper = new DB();
        ObservableList<Task> tasks = databaseHelper.getTasksForSpecificUser(User.getInstance().getUserid());
        if(tasks != null) {
            TableColumn<Task, String> nameColumn = new TableColumn<>("Name");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

            TableColumn<Task, String> descriptionColumn = new TableColumn<>("Description");
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

            TableColumn<Task, String> dateColumn = new TableColumn<>("Due Date");
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

//            TableColumn<Task, String> specialityColumn = new TableColumn<>("Speciality");
//            specialityColumn.setCellValueFactory(new PropertyValueFactory<>("speciality"));


            tableView.setItems((ObservableList<Task>) tasks);
            tableView.getColumns().addAll(nameColumn, descriptionColumn,dateColumn);
        }
    }

}
