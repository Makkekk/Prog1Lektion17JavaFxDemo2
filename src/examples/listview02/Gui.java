package examples.listview02;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Gui extends Application {
    private final TextField nameTextField = new TextField();
    private final ListView<String> namesListView = new ListView<>();
    private final ArrayList<String> names = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("ListView Demo2");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

    private void initContent(GridPane pane) {
        // show or hide grid lines
        pane.setGridLinesVisible(false);
        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        // add a label to the pane (at col=0, row=0)
        Label nameLabel = new Label("Name:");
        pane.add(nameLabel, 0, 0);

        // add a label to the pane (at col=0, row=1)
        Label namesLabel = new Label("Names:");
        pane.add(namesLabel, 0, 1);
        GridPane.setValignment(namesLabel, VPos.TOP);

        // add a text field to the pane (at col=1, row=0)
        pane.add(nameTextField, 1, 0);

        // add a listView to the pane(at col=1, row=1)
        pane.add(namesListView, 1, 1);
        namesListView.setPrefWidth(200);
        namesListView.setPrefHeight(200);
        namesListView.getItems().setAll(this.initNames());

        ChangeListener<String> listener = (ov, oldString, newString) -> selectionChanged();
        namesListView.getSelectionModel().selectedItemProperty().addListener(listener);

        namesListView.getSelectionModel().clearSelection();

        // add a button to the pane (at col=4, row=0)
        Button addButton = new Button("Add");
        pane.add(addButton, 4, 0);
        // btnAdd.setDefaultButton(true);

        // connect a method to the button
        addButton.setOnAction(event -> addAction());

        // add a button to the pane (at col=4, row=1)
        Button deleteButton = new Button("Delete");
        pane.add(deleteButton, 4, 1);

        // connect a method to the button
        deleteButton.setOnAction(event -> deleteAction());
    }

    private void selectionChanged() {
        String selected = namesListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            nameTextField.setText(selected);
        } else {
            nameTextField.clear();
        }
    }

    private void addAction() {
        String name = nameTextField.getText().trim();
        if (name.length() > 0) {
            names.add(name);
            namesListView.getItems().setAll(names);
        }
    }

    private void deleteAction() {
        int index = namesListView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            names.remove(index);
            nameTextField.clear();
            namesListView.getItems().setAll(names);
        }
    }

    private ArrayList<String> initNames() {
        names.add("Jane");
        names.add("Eva");
        names.add("Lene");
        names.add("Mette");
        names.add("Tine");
        names.add("Line");
        names.add("Lone");
        names.add("Alberte");
        names.add("Pia");
        return names;
    }
}
