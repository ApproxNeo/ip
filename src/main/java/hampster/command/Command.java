package hampster.command;


import hampster.exception.HampsterException;
import hampster.task.TaskList;
import hampster.ui.Ui;

public abstract class Command {

    public Command() {
    }

    public abstract void execute(TaskList tasks, Ui ui) throws HampsterException;
}