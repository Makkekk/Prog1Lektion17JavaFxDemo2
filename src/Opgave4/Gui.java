package Opgave4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Gui extends Application {
    private final TextField nameTextField = new TextField();
    private final ListView<Person> peopleListView = new ListView<>();

    private final ArrayList<Person> boys = new ArrayList<Person>();
    private final ArrayList<Person> girls = new ArrayList<Person>();

    private RadioButton boysRadioButton;
    private RadioButton girlsRadioButton;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Names");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        boysRadioButton = new RadioButton("Boys");
        girlsRadioButton = new RadioButton("Girls");

        ToggleGroup group = new ToggleGroup();
        boysRadioButton.setToggleGroup(group);
        girlsRadioButton.setToggleGroup(group);

        boysRadioButton.setSelected(true);

        pane.add(boysRadioButton, 0, 0);
        pane.add(girlsRadioButton, 1, 0);

        boysRadioButton.setOnAction(event -> updateListView());
        girlsRadioButton.setOnAction(event -> updateListView());

        Label nameLabel = new Label("Name:");
        pane.add(nameLabel, 0, 2);

        Label namesLabel = new Label("Names:");
        pane.add(namesLabel, 0, 1);
        pane.add(nameTextField,1,2);

        GridPane.setValignment(namesLabel, VPos.TOP);

        pane.add(peopleListView, 1, 1, 2, 1);
        peopleListView.setPrefWidth(200);
        peopleListView.setPrefHeight(200);

        Button addButton = new Button("Add");
        pane.add(addButton, 3, 2);
        addButton.setOnAction(event -> this.addAction());

        Button deleteButton = new Button("Delete");
        pane.add(deleteButton, 3, 1);
        deleteButton.setOnAction(event -> this.deleteAction());


        initNames();
        updateListView();

    }

    private void initNames() {
        boys.add(new Person("Jens"));
        boys.add(new Person("Hans"));
        boys.add(new Person("Søren"));

        girls.add(new Person("Anne"));
        girls.add(new Person("Sanne"));
        girls.add(new Person("Lis"));

    }

    private void updateListView() {
        peopleListView.getItems().clear();  // Clear the current list view

        if (boysRadioButton.isSelected()) {
            peopleListView.getItems().addAll(boys);  // Display boys' names
        } else {
            peopleListView.getItems().addAll(girls); // Display girls' names
        }
    }

    private void addAction() {
        String name = nameTextField.getText().trim();
        if (name.length() > 0) {
            if (boysRadioButton.isSelected()) {
                boys.add(new Person(name));
            } else {
                girls.add(new Person(name));
            }
            updateListView(); // Refresh the ListView
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add person");
            alert.setHeaderText("No name typed");
            alert.setContentText("Please enter a name.");
            alert.showAndWait();
        }
    }

    private void deleteAction() {
        Person selectedName = peopleListView.getSelectionModel().getSelectedItem();
        if (selectedName != null) {
            if (boysRadioButton.isSelected()) {
                boys.remove(selectedName);
            } else {
                girls.remove(selectedName);
            }
            updateListView(); // Refresh the ListView
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete person");
            alert.setHeaderText("No person selected");
            alert.setContentText("Select a person to be deleted");
            alert.showAndWait();
        }

    }
}

