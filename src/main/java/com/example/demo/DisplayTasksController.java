package com.example.demo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;


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
        ObservableList<Task> tasks = DB.getTasksForSpecificUser(User.getInstance().getUserid());
        if(tasks != null) {
            TableColumn<Task, String> typeColumn = new TableColumn<>("Type");
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

            TableColumn<Task, String> nameColumn = new TableColumn<>("Name");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

            TableColumn<Task, String> descriptionColumn = new TableColumn<>("Description");
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

            TableColumn<Task, String> dateColumn = new TableColumn<>("Due Date");
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

            TableColumn<Task, String> specialityColumn = new TableColumn<>("Speciality");
            specialityColumn.setCellValueFactory(new PropertyValueFactory<>("speciality"));


            tableView.setItems((ObservableList<Task>) tasks);
            tableView.setEditable(true);
            tableView.getColumns().addAll(nameColumn, descriptionColumn,dateColumn,typeColumn,specialityColumn);

            // Add edit and delete context menu
            ContextMenu contextMenu = new ContextMenu();

            MenuItem editItem = new MenuItem("Edit");
            editItem.setOnAction(event -> {
                Task selectedThing = tableView.getSelectionModel().getSelectedItem();
                if (selectedThing != null) {
                    editThing(selectedThing,tasks);
                }
            });

            MenuItem deleteItem = new MenuItem("Delete");
            deleteItem.setOnAction(event -> {
                Task selectedThing = tableView.getSelectionModel().getSelectedItem();
                if (selectedThing != null) {
                    deleteThing(selectedThing,tasks);
                }
            });

            contextMenu.getItems().addAll(editItem, deleteItem);
            tableView.setContextMenu(contextMenu);
        }



    }

    private void editThing(Task task, ObservableList<Task> tasks) {
        TextInputDialog dialog = new TextInputDialog(task.getTitle());
        dialog.setTitle("Edit Task");
        dialog.setHeaderText("Edit the properties of the selected task:");
        dialog.setContentText("Title:");

        // Create input fields for editing other properties
        TextField descriptionField = new TextField(task.getDescription());
        DatePicker dueDateField = new DatePicker();
        TextField specialityField = new TextField(task.getSpeciality());

//        dueDateField.setOnAction(event -> {
//            // Get the selected date
//            selectedDate= dueDateField.getValue());
//        });
        // Add the input fields to the dialog
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(new Label("Title:"), 0, 0);
        grid.add(dialog.getEditor(), 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(descriptionField, 1, 1);
        grid.add(new Label("Due Date:"), 0, 2);
        grid.add(new DatePicker(), 1, 2);
        grid.add(dueDateField, 1, 2);
        grid.add(new Label("Speciality:"), 0, 3);
        grid.add(specialityField, 1, 3);
        dialog.getDialogPane().setContent(grid);

        // Show the dialog and update the task object if the user clicked OK
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            DB.updateTaskInDatabase(task.getId(), dialog.getEditor().getText(),descriptionField.getText(),specialityField.getText(), Date.valueOf(dueDateField.getValue()),task.getType(),User.getInstance().getUserid());
            tasks = DB.getTasksForSpecificUser(User.getInstance().getUserid());
            tableView.setItems(tasks);
        }
    }

    private void deleteThing(Task thing,ObservableList<Task> tasks) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Are you sure you want to delete this thing?");
        alert.setContentText("Name: " + thing.getTitle() + "\nDescription: " + thing.getDescription());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DB.removeTaskFromDatabase(thing.getId());
            tasks = DB.getTasksForSpecificUser(User.getInstance().getUserid());
            tableView.setItems((ObservableList<Task>) tasks);
        }
    }





    }


