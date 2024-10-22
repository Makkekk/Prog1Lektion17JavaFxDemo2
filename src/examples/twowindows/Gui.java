package examples.twowindows;

import examples.twowindows.models.Movie;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Gui extends Application {
    private final TextField resultTextField = new TextField();
    private MovieInputWindow movieWindow;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Gui Demo input window");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

        movieWindow = new MovieInputWindow("Create a movie", stage);

    }
    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label nameLabel = new Label("Movie:");
        pane.add(nameLabel, 0, 0);

        pane.add(resultTextField, 1, 0, 2, 1);
        resultTextField.setEditable(false);

        Button createMovieButton = new Button("Create movie");
        pane.add(createMovieButton, 1, 1);
        GridPane.setMargin(createMovieButton, new Insets(10, 10, 0, 10));
        createMovieButton.setOnAction(event -> this.createMovieAction());

    }

    private void createMovieAction() {
        movieWindow.showAndWait();
        // wait for the dialog to close ...
        if (movieWindow.getActualMovie() != null) {
            Movie movie = movieWindow.getActualMovie();
            resultTextField.setText(movie.toString());
        }
    }
}
