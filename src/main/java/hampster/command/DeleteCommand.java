package hampster.command;

import hampster.exception.HampsterException;
import hampster.task.TaskList;
import hampster.ui.Ui;

/** Command that removes a task from the task list. */
public class DeleteCommand extends Command {
    private int taskNumber;

    /** Creates a delete command for the requested task number. */
    public DeleteCommand(String[] parts) throws HampsterException {
        if (parts.length != 2) {
            throw new HampsterException(
                    "Delete needs exactly one task number. Try: delete <task number>");
        }

        try {
            taskNumber = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new HampsterException(
                    "'" + parts[1] + "' ain't a task number.");
        }

        if (taskNumber < 1) {
            throw new HampsterException("Delete doesn't accept negative numbers");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws HampsterException {
        if (taskNumber >= tasks.size()) {
            throw new HampsterException("Task " + taskNumber + " doesn't exist.");
        }

        tasks.remove(taskNumber);

        ui.showMessage("\tNoted broh. I've removed this task:");
        ui.showMessage("\t  " + tasks.get(taskNumber));
        ui.showMessage("\tNow you've got " + tasks.size() + " tasks in the list.");

    }
}
