package hampster.command;


import hampster.exception.HampsterException;
import hampster.task.TaskList;
import hampster.ui.Ui;

/** Represents an instruction that can be executed by Hampster. */
public abstract class Command {

    public Command() {
    }

    /** Executes this command against the task list and user interface. */
    public abstract void execute(TaskList tasks, Ui ui) throws HampsterException;
}
