package hampster.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import hampster.HampsterException;
import hampster.parser.DateTimeParser;
import hampster.task.Event;
import hampster.task.Task;
import hampster.ui.Ui;

public class EventCommand extends Command {
    String description;
    LocalDateTime from;
    LocalDateTime to;

    public EventCommand(String[] parts) throws HampsterException {
        String input = Arrays.stream(parts)
                .skip(1)
                .collect(Collectors.joining(" "))
                .trim();

        if (input.isEmpty()) {
            throw new HampsterException(
                    "Events need a description.");
        }

        String[] eventParts = input.split("\\s+/from\\s+", 2);

        if (eventParts.length != 2) {
            throw new HampsterException(
                    "Events need a /from time. "
                            + "Try: event <description> /from <start> /to <end>");
        }

        description = eventParts[0].trim();
        String[] timeParts = eventParts[1].split("\\s+/to\\s+", 2);

        if (timeParts.length != 2) {
            throw new HampsterException(
                    "Events need a /to time."
                            + "Try: event <description> /from <start> /to <end>");
        }

        try {
            from = DateTimeParser.parse(timeParts[0]);
            to = DateTimeParser.parse(timeParts[1]);

        } catch (DateTimeParseException e) {
            throw new HampsterException(e.getMessage());
        }

        if (description.isEmpty()) {
            throw new HampsterException(
                    "Tell me what the event actually is.");
        }
    }
    
    @Override
    public void execute(List<Task> tasks, Ui ui) {
        tasks.add(new Event(false, description, from, to));

        ui.showMessage("\tEvent secured broh.");
        ui.showMessage("\t" + tasks.get(tasks.size() - 1));
        ui.showMessage("\tYou've got " + tasks.size() + " tasks now.");    
    }
}
