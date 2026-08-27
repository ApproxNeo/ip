package hampster.parser;

import hampster.exception.HampsterException;
import hampster.command.ByeCommand;
import hampster.command.Command;
import hampster.command.DeadlineCommand;
import hampster.command.DeleteCommand;
import hampster.command.EventCommand;
import hampster.command.FindCommand;
import hampster.command.ListCommand;
import hampster.command.MarkCommand;
import hampster.command.ToDoCommand;

public class CommandParser {

    public static Command parse(String userInput) throws HampsterException {
        String[] parts = userInput.trim().split("\\s+");

        String cmd = parts[0].toUpperCase();
        return switch (cmd) {
            case "BYE" -> new ByeCommand(parts);
            case "LIST" -> new ListCommand(parts);
            case "MARK" -> new MarkCommand(parts);
            case "FIND" -> new FindCommand(parts);
            case "DELETE" -> new DeleteCommand(parts);
            case "TODO" -> new ToDoCommand(parts);
            case "DEADLINE" -> new DeadlineCommand(parts);
            case "EVENT" -> new EventCommand(parts);
            default -> throw new HampsterException(
                    "invalid instruction");
        };
    }
}
