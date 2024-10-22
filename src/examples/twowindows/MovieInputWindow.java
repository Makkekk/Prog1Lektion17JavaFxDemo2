package examples.twowindows;

import examples.twowindows.models.Movie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MovieInputWindow extends Stage {
    private final TextField titleTextField = new TextField();
    private final TextField actorTextField = new TextField();

    private Movie actualMovie = null;

    public MovieInputWindow(String title, Stage owner) {
        this.initOwner(owner);
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setMinHeight(100);
        this.setMinWidth(200);
        this.setResizable(false);

        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }


    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label titleLabel = new Label("Titel:");
        pane.add(titleLabel, 0, 0);

        Label actorLabel = new Label("Actor:");
        pane.add(actorLabel, 0, 1);

        pane.add(titleTextField, 1, 0);

        pane.add(actorTextField, 1, 1);

        HBox buttonBox = new HBox(20);
        pane.add(buttonBox, 0, 2, 2, 1);
        buttonBox.setPadding(new Insets(10, 10, 0, 10));
        buttonBox.setAlignment(Pos.CENTER);

        Button cancelButton = new Button("Cancel");
        buttonBox.getChildren().add(cancelButton);
        cancelButton.setOnAction(event -> this.cancelAction());

        Button okButton = new Button("OK");
        buttonBox.getChildren().add(okButton);
        okButton.setOnAction(event -> this.okAction());
    }


    private void cancelAction() {
        titleTextField.clear();
        titleTextField.requestFocus();
        actorTextField.clear();
        actualMovie = null;
        MovieInputWindow.this.hide();
    }

    private void okAction() {
        String title = titleTextField.getText().trim();
        String actor = actorTextField.getText().trim();

        if (title.length() > 0 && actor.length() > 0) {
            actualMovie = new Movie(title, actor);
            titleTextField.clear();
            actorTextField.clear();
            titleTextField.requestFocus();
            MovieInputWindow.this.hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Create movie");
            alert.setHeaderText("Information missing");
            alert.setContentText("Type title and actor");
            alert.show();
        }
    }


    public Movie getActualMovie() {
        return actualMovie;
    }

    public void clearActualMovie() {
        actualMovie = null;
    }
}