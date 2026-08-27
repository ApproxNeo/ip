package hampster.parser;

import hampster.exception.HampsterException;
import hampster.command.ByeCommand;
import hampster.command.Command;
import hampster.command.DeadlineCommand;
import hampster.command.ListCommand;
import hampster.command.MarkCommand;
import hampster.command.ToDoCommand;
import hampster.command.DeleteCommand;
import hampster.command.EventCommand;

public class CommandParser {

    public static Command parse(String userInput) throws HampsterException {
        String[] parts = userInput.trim().split("\\s+");

        String cmd = parts[0].toUpperCase();
        return switch (cmd) {
            case "BYE" -> new ByeCommand(parts);
            case "LIST" -> new ListCommand(parts); // handleList(tasks);
            case "MARK" -> new MarkCommand(parts);
            // case "UNMARK" -> handleMark(parts, tasks, false);
            case "DELETE" -> new DeleteCommand(parts);
            case "TODO" -> new ToDoCommand(parts);
            case "DEADLINE" -> new DeadlineCommand(parts);
            case "EVENT" -> new EventCommand(parts);
            default -> throw new HampsterException(
                    "invalid instruction");
        };
    }
}
