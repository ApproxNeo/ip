package hampster.command;

import hampster.exception.HampsterException;
import hampster.task.TaskList;
import hampster.ui.Ui;

/** Command that removes a task from the task list. */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /** Creates a delete command for the requested task number. */
    public DeleteCommand(String[] parts) throws HampsterException {
        taskNumber = parseTaskNumber(parts, "Delete");
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws HampsterException {
        if (taskNumber > tasks.size()) {
            throw new HampsterException("Task " + taskNumber + " doesn't exist.");
        }

        String deletedTask = tasks.get(taskNumber - 1).toString();
        tasks.remove(taskNumber - 1);

        ui.showMessage("\tNoted broh. I've removed this task:");
        ui.showMessage("\t  " + deletedTask);
        ui.showMessage("\tNow you've got " + tasks.size() + " tasks in the list.");

    }
}
