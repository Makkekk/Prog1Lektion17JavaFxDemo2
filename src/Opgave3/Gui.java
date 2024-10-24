package Opgave3;

import Opgave3.Model.Person;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Gui extends Application {
    private final ListView<Person> namesListView = new ListView<>();
    private final ObservableList<Person> persons = namesListView.getItems();

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

        Label Personlabel = new Label("Persons:");
        pane.add(Personlabel, 0, 0);

        pane.add(namesListView, 1, 0, 1, 5);
        namesListView.setPrefHeight(200);

        Button addpersonButton = new Button("Add person");
        pane.add(addpersonButton, 2, 0);

        GridPane.setMargin(addpersonButton, new Insets(10, 10, 0, 10));
        addpersonButton.setOnAction(event -> openPersonDialog());


        persons.add(new Person("Ulla Hansen", "Direktør", true));
        persons.add(new Person("Søren Sørensen", "Landmand", false));
        persons.add(new Person("Pia Madsen", "Mekaniker", true));
        persons.add(new Person("Hans Madsen", "Underviser", false));
    }
        private void openPersonDialog() {
            // Open the dialog window to add a person
            PersonDialog dialog = new PersonDialog();
            dialog.showAndWait().ifPresent(person -> persons.add(person));
        }
}