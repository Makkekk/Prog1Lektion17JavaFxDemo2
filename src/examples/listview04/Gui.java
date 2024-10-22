package examples.listview04;

import examples.listview04.models.Person;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Gui extends Application {
    private final TextField nameTextField = new TextField();
    private final ListView<Person> peopleListView = new ListView<>();
    private final ObservableList<Person> people = peopleListView.getItems();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("ListView Demo4");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void initContent(GridPane pane) {
        this.initPersons();

        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label nameLabel = new Label("Name:");
        pane.add(nameLabel, 0, 0);

        Label namesLabel = new Label("Names:");
        pane.add(namesLabel, 0, 1);
        GridPane.setValignment(namesLabel, VPos.TOP);

        pane.add(nameTextField, 1, 0);

        pane.add(peopleListView, 1, 1);
        peopleListView.setPrefWidth(200);
        peopleListView.setPrefHeight(200);

        ChangeListener<Person> listener = (ov, o, newPerson) -> this.personsItemSelected(newPerson);
        peopleListView.getSelectionModel().selectedItemProperty().addListener(listener);

        Button addButton = new Button("Add");
        pane.add(addButton, 2, 0);
        addButton.setOnAction(event -> this.addAction());

        Button deleteButton = new Button("Delete");
        pane.add(deleteButton, 2, 1);
        deleteButton.setOnAction(event -> this.deleteAction());
    }

    private void initPersons() {
        people.add(new Person("Jens", "Jensen", "jj@eaaa.dk"));
        people.add(new Person("Hans", "Hansen", "hh@eaaa.dk"));
        people.add(new Person("Pia", "Peters", "pp@eaaa.dk"));
    }


    private void personsItemSelected(Person selected) {
        if (selected != null) {
            nameTextField.setText(selected.toString());
        } else {
            nameTextField.clear();
        }
    }

    private void addAction() {
        String name = nameTextField.getText().trim();
        if (name.length() > 0) {
            Person p = new Person(name, "Hansen", name + "@mail.com");
            people.add(p);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Add person");
            alert.setHeaderText("No named typed");
            alert.setContentText("Type the name of the person");
            alert.show();
            // wait for the modal dialog to close
        }
    }

    private void deleteAction() {
        int index = peopleListView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            people.remove(index);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete person");
            alert.setHeaderText("No person selected");
            alert.setContentText("Select a person to be deleted");
            alert.show();
            // wait for the modal dialog to close
        }
    }
}
