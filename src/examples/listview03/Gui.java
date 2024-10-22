package examples.listview03;

import examples.listview03.models.Person;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Gui extends Application {
    private final ListView<Person> personListView = new ListView<>();
    private final ObservableList<Person> persons = personListView.getItems();
    private final TextField nameTextField = new TextField();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("ListView Demo3");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void initContent(GridPane pane) {
        initPersons();
        // show or hide grid lines
        pane.setGridLinesVisible(false);
        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        Label nameLabel = new Label("Name:");
        pane.add(nameLabel, 0, 0);

        Label namesLabel = new Label("Names:");
        pane.add(namesLabel, 0, 1);

        pane.add(nameTextField, 1, 0, 2, 1);

        // add a listView to the pane(at col=1, row=0)
        pane.add(personListView, 1, 1, 1, 5);
        personListView.setPrefWidth(200);
        personListView.setPrefHeight(200);

        ChangeListener<Person> listener = (ov, oldPerson, newPerson) -> this.selectionChanged(newPerson);
        personListView.getSelectionModel().selectedItemProperty().addListener(listener);

        personListView.getSelectionModel().clearSelection();

        Button addButton = new Button("Add");
        pane.add(addButton, 6, 0);

        // connect a method to the button
        addButton.setOnAction(event -> this.addAction());

        Button deleteButton = new Button("Delete");
        pane.add(deleteButton, 6, 3);

        // connect a method to the button
        deleteButton.setOnAction(event -> this.deleteAction());

    }

    private void addAction() {
        String name = nameTextField.getText().trim();
        if (name.length() > 0) {
            Person p = new Person(name, "Hansen", name + "@mail.com");
            persons.add(p);
        }
    }

    private void deleteAction() {
        int index = personListView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            persons.remove(index);
        }
    }

    private void initPersons() {
        persons.add(new Person("Jens", "Jensen", "jens@eaaa.dk"));
        persons.add(new Person("Hans", "Hansen", "hans@eaaa.dk"));
        persons.add(new Person("Pia", "Peters", "pia @eaaa.dk"));
    }


    private void selectionChanged(Person newPerson) {
        if (newPerson != null) {
            nameTextField.setText(newPerson.getFirstName());
        } else {
            nameTextField.clear();
        }
    }
}
