package hampster.command;

import java.util.List;

import hampster.HampsterException;
import hampster.task.Task;
import hampster.ui.Ui;

public abstract class Command {

    public Command() {
    }

    public abstract void execute(List<Task> tasks, Ui ui) throws HampsterException;
}