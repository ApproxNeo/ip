package hampster.command;

import java.util.Arrays;
import java.util.stream.Collectors;

import hampster.exception.HampsterException;
import hampster.parser.TagParser;
import hampster.task.TaskList;
import hampster.task.ToDo;
import hampster.ui.Ui;

/** Command that creates a todo task. */
public class ToDoCommand extends Command {
    private final String description;
    private final String tag;

    /** Creates a todo command from the user's input parts. */
    public ToDoCommand(String[] parts) throws HampsterException {
        String input = Arrays.stream(parts)
                .skip(1)
                .collect(Collectors.joining(" "))
                .trim();

        TagParser.ParsedInput parsedInput = TagParser.parseOption(input);
        description = parsedInput.description();
        tag = parsedInput.tag();

        if (description.isEmpty()) {
            throw new HampsterException(
                    "ToDo needs a description.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        System.out.println(description);
        tasks.add(new ToDo(false, description, tag));

        ui.showMessage(
            "\tAight, added that todo broh.",
            "\t" + tasks.get(tasks.size() - 1),
            "\tYou've got " + tasks.size() + " tasks now.");
    }
}
