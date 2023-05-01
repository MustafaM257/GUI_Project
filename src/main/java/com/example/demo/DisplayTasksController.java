package com.example.demo;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class DisplayTasksController implements Initializable {
    @FXML
    private TableView<Task> tableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
