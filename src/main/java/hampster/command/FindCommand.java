package hampster.command;

import hampster.exception.HampsterException;
import hampster.task.TaskList;
import hampster.ui.Ui;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String[] parts) throws HampsterException {
        if (parts.length != 2) {
            throw new HampsterException(
                    "Find command requires exactly one keyword.");
        }

        keyword = parts[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        TaskList matchingTasks = tasks.find(keyword);

        ui.showMessage("\tHere are the matching tasks broh:");

        for (int i = 0; i < matchingTasks.size(); i++) {
            ui.showMessage("\t" + (i + 1) + ". " + matchingTasks.get(i));
        }
    }
}