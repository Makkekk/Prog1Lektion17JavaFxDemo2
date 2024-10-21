package examples.radiobutton;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Gui extends Application {
    private final Label textLabel = new Label("RadioButton example");
    private final ToggleGroup group = new ToggleGroup();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Radiobutton Demo");
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

        // add a HBox (horizontal box) to the pane (at col=0, row=0)
        HBox box = new HBox();
        pane.add(box, 0, 0);

        String[] colorStrings = { "Red", "Blue", "Green", "Black" };
        Color[] colors = { Color.RED, Color.BLUE, Color.GREEN, Color.BLACK };

        // add 4 radio buttons to the box
        for (int i = 0; i < colors.length; i++) {
            RadioButton radioButton = new RadioButton();
            box.getChildren().add(radioButton);
            radioButton.setText(colorStrings[i]);
            radioButton.setPrefWidth(70);
            // make the radio button remember its own color
            radioButton.setUserData(colors[i]);
            // ensure that only one radio button is in selected state
            radioButton.setToggleGroup(group);
            // set the method to execute when a radio button is selected
            radioButton.setOnAction(event -> setLabelColor());
        }

        // add a centered label to the pane (at col=0, row=1)
        pane.add(textLabel, 0, 1);
        GridPane.setHalignment(textLabel, HPos.CENTER);

        // select first radio button and update color of lblText
        RadioButton redColorRadioButton = (RadioButton) group.getToggles().get(0);
        redColorRadioButton.setSelected(true);
        textLabel.setTextFill(colors[0]);
    }

    private void setLabelColor() {
        RadioButton radioButton = (RadioButton) group.getSelectedToggle();
        Color color = (Color) radioButton.getUserData();
        textLabel.setTextFill(color);
    }
}
