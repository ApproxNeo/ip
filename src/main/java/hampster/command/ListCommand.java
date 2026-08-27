package hampster.command;

import hampster.exception.HampsterException;
import hampster.task.TaskList;
import hampster.ui.Ui;

public class ListCommand extends Command {

    public ListCommand(String[] parts) throws HampsterException {
        if (parts.length != 1) {
            throw new HampsterException("List does not accept any arguments");
        }
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showMessage("\tListing your tasks broh");
        for (int i = 0; i < tasks.size(); ++i) {
            ui.showMessage("\t" + (i + 1) + ". " + tasks.get(i));
        }
    }
}
