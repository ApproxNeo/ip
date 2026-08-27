package hampster.command;

import java.util.List;

import hampster.HampsterException;
import hampster.task.Task;
import hampster.ui.Ui;

public class ByeCommand extends Command {

    public ByeCommand(String[] parts) throws HampsterException {
        if (parts.length != 1) {
            throw new HampsterException("Bye does not accept any arguments");
        }
    }
    
    @Override
    public void execute(List<Task> tasks, Ui ui) {
        ui.shutdown();
    }
}
