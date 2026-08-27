package hampster.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.stream.Collectors;

import hampster.exception.HampsterException;
import hampster.parser.DateTimeParser;
import hampster.task.Deadline;
import hampster.task.TaskList;
import hampster.ui.Ui;

public class DeadlineCommand extends Command {
    String description;
    LocalDateTime by;

    public DeadlineCommand(String[] parts) throws HampsterException {
        String input = Arrays.stream(parts)
                .skip(1)
                .collect(Collectors.joining(" "))
                .trim();

        if (input.isEmpty()) {
            throw new HampsterException(
                    "Deadlines need a description.");
        }

        String[] deadlineParts = input.split("\\s+/by\\s+", 2);

        if (deadlineParts.length != 2) {
            throw new HampsterException(
                    "Deadlines need a /by. "
                            + "Try: deadline <description> /by <date or time>");
        }

        description = deadlineParts[0].trim();

        try {
            by = DateTimeParser.parse(deadlineParts[1]);
        } catch (DateTimeParseException e) {
            throw new HampsterException(e.getMessage());
        }
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.add(new Deadline(false, description, by));

        ui.showMessage("\tDeadline locked in.");
        ui.showMessage("\t" + tasks.get(tasks.size() - 1));
        ui.showMessage("\tYou've got " + tasks.size() + " tasks now.");
    
    }
}
