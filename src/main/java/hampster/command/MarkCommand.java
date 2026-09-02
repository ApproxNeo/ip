package hampster.command;

import hampster.exception.HampsterException;
import hampster.task.TaskList;
import hampster.ui.Ui;

/** Command that toggles the completion status of a task. */
public class MarkCommand extends Command {
    private int taskNumber;

    /** Creates a mark command for the requested task number. */
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
    public void execute(TaskList tasks, Ui ui) throws HampsterException {
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
