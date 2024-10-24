package Opgave3;

import Opgave3.Model.Person;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class PersonDialog extends Dialog<Person> {
    private final TextField nameTextField = new TextField();
    private final TextField titleTextField = new TextField();
    private final CheckBox seniorCheckbox = new CheckBox("Senior");

    public PersonDialog() {
        setTitle("Add New Person");

        // Set the buttons for the dialog (OK and Cancel)
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Create a layout for the dialog
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Add input fields and labels to the layout
        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameTextField, 1, 0);
        grid.add(new Label("Title:"), 0, 1);
        grid.add(titleTextField, 1, 1);
        grid.add(seniorCheckbox, 1, 2);

        // Set the grid as the content of the dialog
        getDialogPane().setContent(grid);

        // When the user clicks OK, create a Person object
        setResultConverter(button -> {
            if (button == ButtonType.OK) {
                String name = nameTextField.getText().trim();
                String title = titleTextField.getText().trim();
                boolean isSenior = seniorCheckbox.isSelected();
                return new Person(name, title, isSenior);
            }
            return null;  // Return null if "Cancel" is clicked
        }
        );
    }
}
