package hampster.gui;

import java.io.IOException;

import hampster.command.Command;
import hampster.exception.HampsterException;
import hampster.parser.CommandParser;
import hampster.storage.Storage;
import hampster.task.TaskList;
import hampster.ui.Ui;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/** Controls the main FXML-based Hampster window. */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    private TaskList tasks;

    private final Ui outputUi = new Ui() {
        @Override
        public void showMessage(String... messages) {
            for (String message : messages) {
                dialogContainer.getChildren().add(createMessage(message, false));
            }
        }

        @Override
        public void printLine() {
            // Separators are intentionally omitted from the chat view.
        }

        @Override
        public void shutdown() {
            // The GUI remains open until its window is closed.
        }
    };

    /** Initializes bindings for the main window controls. */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        try {
            tasks = Storage.load();
        } catch (IOException exception) {
            tasks = new TaskList();
        }

        dialogContainer.getChildren().add(createMessage(
                "Heh Heh Wasup broh I'm Hampster.\nWhaddya want?", false));
    }

    /** Handles a command submitted through the GUI input field. */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();

        if (input.isEmpty()) {
            return;
        }

        dialogContainer.getChildren().add(createMessage(input, true));
        userInput.clear();

        try {
            Command command = CommandParser.parse(input);
            command.execute(tasks, outputUi);
            Storage.save(tasks);
        } catch (HampsterException exception) {
            dialogContainer.getChildren().add(createMessage(
                    "Broh... " + exception.getMessage(), false));
        }
    }

    /** Creates a styled chat message for the selected speaker. */
    private HBox createMessage(String message, boolean fromUser) {
        Label label = new Label(message);
        label.setWrapText(true);
        label.setMaxWidth(280.0);
        label.getStyleClass().add(fromUser ? "user-message" : "hampster-message");

        HBox box = new HBox(label);
        box.setAlignment(fromUser ? Pos.TOP_RIGHT : Pos.TOP_LEFT);
        box.setMaxWidth(Double.MAX_VALUE);
        box.getStyleClass().add("message-row");
        return box;
    }
}
