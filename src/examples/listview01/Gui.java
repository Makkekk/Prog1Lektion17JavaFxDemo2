package examples.listview01;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Gui extends Application {
    private final TextField nameTextfield = new TextField();
    private final ListView<String> namesListView = new ListView<>();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("ListView Demo1");
        GridPane pane = new GridPane();
        initContent(pane);
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
        pane.add(nameTextfield, 1, 0);

        // add a listView to the pane(at col=1, row=1)
        pane.add(namesListView, 1, 1);
        namesListView.setPrefWidth(200);
        namesListView.setPrefHeight(200);

        ChangeListener<String> listener = (ov, o, n) -> this.selectionChanged();
        namesListView.getSelectionModel().selectedItemProperty().addListener(listener);
        
        namesListView.getItems().setAll(this.initNames());

    }

    private void selectionChanged() {
        String selected = namesListView.getSelectionModel().getSelectedItem();
        nameTextfield.setText(selected);
    }

    private ArrayList<String> initNames() {
        ArrayList<String> names = new ArrayList<>();
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
