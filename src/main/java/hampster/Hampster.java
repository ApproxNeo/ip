package hampster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hampster.task.Task;
import hampster.ui.Ui;
import hampster.storage.Storage;
import hampster.parser.CommandParser;
import hampster.command.Command;;

public class Hampster {

    private static Ui ui;
    public static void main(String[] args) {
        ui = new Ui();
        ui.showWelcome();
        
        List<Task> tasks;
        
        try {
            tasks = Storage.load();
        } catch (IOException e) {
            tasks = new ArrayList<>();
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
