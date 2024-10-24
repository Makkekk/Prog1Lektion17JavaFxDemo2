package Opgave3;

import Opgave1.Model.Person;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Gui extends Application {
    private final TextField nameTextField = new TextField();
    private final TextField titleTextField = new TextField();
    private final ListView<Person> namesListView = new ListView<>();
    private final ObservableList<Person> persons = namesListView.getItems();
    private final CheckBox seniorCheckbox = new CheckBox("Senior");

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Person adminstration");
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

        Label nameLabel = new Label("Name:");
        pane.add(nameLabel, 0, 0);
        Label titleLabel = new Label("Title:");
        pane.add(titleLabel, 0, 1);
        Label Personlabel = new Label("Persons:");
        pane.add(Personlabel, 0, 3);


        pane.add(nameTextField, 1, 0, 2, 1);
        nameTextField.setEditable(true);

        pane.add(titleTextField, 1, 1, 2, 1);

        pane.add(seniorCheckbox,1,2);

        pane.add(namesListView, 1, 3, 1, 5);
        namesListView.setPrefHeight(200);

        Button addpersonButton = new Button("Add person");
        pane.add(addpersonButton, 6, 0);
        GridPane.setMargin(addpersonButton, new Insets(10, 10, 0, 10));

        addpersonButton.setOnAction(event -> addPerson());
    }

    private void addPerson() {
        String name = nameTextField.getText().trim();
        String title = titleTextField.getText().trim();
        boolean isSenior = seniorCheckbox.isSelected();

        if (!name.isEmpty() && !title.isEmpty()) {
            Person person = new Person(name, title, isSenior);
            persons.add(person);

            nameTextField.clear();
            titleTextField.clear();
            seniorCheckbox.setSelected(false);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Please enter both name and title.");
            alert.showAndWait();

        }

    }

}