package hampster.command;

import hampster.exception.HampsterException;
import hampster.task.TaskList;
import hampster.ui.Ui;

/** Command that toggles the completion status of a task. */
public class MarkCommand extends Command {
    private final int taskNumber;

    /** Creates a mark command for the requested task number. */
    public MarkCommand(String[] parts) throws HampsterException {
        taskNumber = parseTaskNumber(parts, "Mark");
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws HampsterException {
        assert tasks != null : "Task list must not be null";
        assert taskNumber >= 1 : "Task numbers are 1-based";
        assert taskNumber <= tasks.size() : "Task number must be within the current list";

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
