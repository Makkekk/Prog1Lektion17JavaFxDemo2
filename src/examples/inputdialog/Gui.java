package examples.inputdialog;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Optional;

public class Gui extends Application {
    private final TextArea textArea = new TextArea();

    @Override
    public void start(Stage stage)  {
        stage.setTitle("Gui Demo InputDialog");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);

        Label textAreaLabel = new Label("TextArea:");
        pane.add(textAreaLabel, 0, 0);

        pane.add(textArea, 0, 1);
        textArea.setPrefRowCount(7);
        textArea.setPrefWidth(270);
        textArea.setEditable(false);

        Button addTextLineButton = new Button("Add textline");
        pane.add(addTextLineButton, 1, 1);
        addTextLineButton.setOnAction(event -> this.fillAction());
    }
    private void fillAction() {
        Dialog<String> dialog = new TextInputDialog();
        dialog.setTitle("Input text");
        dialog.setHeaderText("Enter some text:");

        Optional<String> result = dialog.showAndWait();

        // wait for the modal dialog to close

        String input = "";
        if (result.isPresent()) {
            input = result.get();
            if (input.length() > 0) {
                textArea.setText(textArea.getText() + input + "\n");
            }
        }
    }
}
