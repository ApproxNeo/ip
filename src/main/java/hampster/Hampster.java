package hampster;

import java.io.IOException;

import hampster.ui.Ui;
import hampster.storage.Storage;
import hampster.parser.CommandParser;
import hampster.command.Command;
import hampster.exception.HampsterException;
import hampster.task.TaskList;;

public class Hampster {

    private static Ui ui;
    public static void main(String[] args) {
        ui = new Ui();
        ui.showWelcome();
        
        TaskList tasks;
        
        try {
            tasks = Storage.load();
        } catch (IOException e) {
            tasks = new TaskList();
        }

        while (true) {
            String userInput = ui.readLine();
            ui.printLine();

            try {
                if (userInput.isBlank()) {
                    throw new HampsterException("You didn't say anything.");
                }

                Command command = CommandParser.parse(userInput);
                command.execute(tasks, ui);
                Storage.save(tasks);

            } catch (HampsterException e) {
                ui.showMessage("\tBroh... " + e.getMessage());
            }
            ui.printLine();
        }
    }
}
