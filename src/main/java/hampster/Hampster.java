package hampster;

import java.io.IOException;

import hampster.command.Command;
import hampster.exception.HampsterException;
import hampster.parser.CommandParser;
import hampster.storage.Storage;
import hampster.task.TaskList;
import hampster.ui.Ui;

/** Runs the Hampster command-line task manager. */
public class Hampster {

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();

        TaskList tasks;

        try {
            tasks = Storage.load();
        } catch (IOException exception) {
            tasks = new TaskList();
        }

        while (true) {
            String userInput = ui.readLine();
            ui.printLine();

            try {
                if (userInput.isBlank()) {
                    throw new HampsterException("Silence? How delightfully ominous. Give me a command, minion.");
                }

                Command command = CommandParser.parse(userInput);
                command.execute(tasks, ui);
                Storage.save(tasks);
            } catch (HampsterException exception) {
                ui.showMessage("\tPathetic! That command has failed: " + exception.getMessage());
            }

            ui.printLine();
        }
    }
}
