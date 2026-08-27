package hampster.command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import hampster.HampsterException;
import hampster.task.Task;
import hampster.task.ToDo;
import hampster.ui.Ui;

public class ToDoCommand extends Command {
    String description;

    public ToDoCommand(String[] parts) throws HampsterException {
        description = Arrays.stream(parts)
                .skip(1)
                .collect(Collectors.joining(" "))
                .trim();

        if (description.isEmpty()) {
            throw new HampsterException(
                    "ToDo needs a description.");
        }
    }
    
    @Override
    public void execute(List<Task> tasks, Ui ui) {
        System.out.println(description);
        tasks.add(new ToDo(description));

        ui.showMessage("\tAight, added that todo broh.");
        ui.showMessage("\t" + tasks.get(tasks.size() - 1));
        ui.showMessage("\tYou've got " + tasks.size() + " tasks now.");
    }
}
