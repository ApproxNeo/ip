package hampster.command;

import java.util.List;

import hampster.HampsterException;
import hampster.task.Task;
import hampster.ui.Ui;

public class MarkCommand extends Command {
    int taskNumber;

    public MarkCommand(String[] parts) throws HampsterException {
        if (parts.length != 2) {
            throw new HampsterException(
                    "Mark needs exactly one task number. Try: mark <task number>");
        }

        try {
            taskNumber = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new HampsterException(
                    "'" + parts[1] + "' ain't a task number.");
        }

        if (taskNumber < 1) {
            throw new HampsterException("Mark doesn't accept negative numbers");
        }
    }

    @Override
    public void execute(List<Task> tasks, Ui ui) throws HampsterException {
        if (taskNumber > tasks.size()) {
            throw new HampsterException("Task " + taskNumber + " doesn't exist.");
        }
        
        if (tasks.get(taskNumber).toggleState()) {
            ui.showMessage("\tBoom. Task " + taskNumber + " is donezo.");
        } else {
            ui.showMessage("\tAight. Task " + taskNumber + " is back in action.");
        }

        ui.showMessage("\t" + tasks.get(taskNumber));

    }
}
