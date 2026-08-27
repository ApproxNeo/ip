package hampster.command;

import hampster.exception.HampsterException;
import hampster.task.TaskList;
import hampster.ui.Ui;
public class ByeCommand extends Command {

    public ByeCommand(String[] parts) throws HampsterException {
        if (parts.length != 1) {
            throw new HampsterException("Bye does not accept any arguments");
        }
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.shutdown();
    }
}
