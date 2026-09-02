package hampster.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/** Controls the main FXML-based Hampster window. */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    /** Initializes bindings for the main window controls. */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Handles a command submitted through the GUI input field. */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();

        if (!input.isEmpty()) {
            dialogContainer.getChildren().add(new javafx.scene.control.Label(input));
            userInput.clear();
        }
    }
}
