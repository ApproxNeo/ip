package hampster.command;

import java.util.List;

import hampster.HampsterException;
import hampster.task.Task;
import hampster.ui.Ui;

public class ListCommand extends Command {

    public ListCommand(String[] parts) throws HampsterException {
        if (parts.length != 1) {
            throw new HampsterException("List does not accept any arguments");
        }
    }
    
    @Override
    public void execute(List<Task> tasks, Ui ui) {
        ui.showMessage("\tListing your tasks broh");
        for (int i = 0; i < tasks.size(); ++i) {
            ui.showMessage("\t" + (i + 1) + ". " + tasks.get(i));
        }
    }
}
