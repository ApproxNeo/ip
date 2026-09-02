package hampster.parser;

import hampster.command.ByeCommand;
import hampster.command.Command;
import hampster.command.DeadlineCommand;
import hampster.command.DeleteCommand;
import hampster.command.EventCommand;
import hampster.command.FindCommand;
import hampster.command.ListCommand;
import hampster.command.MarkCommand;
import hampster.command.ToDoCommand;
import hampster.exception.HampsterException;

/**
 * Parses user input into the corresponding Hampster command.
 */
public class CommandParser {

    /**
     * Converts a user instruction into a {@link Command} object.
     *
     * <p>Command names are case-insensitive. Arguments are separated using
     * one or more whitespace characters.</p>
     *
     * @param userInput the instruction entered by the user
     * @return the command represented by the user input
     * @throws HampsterException if the instruction is not recognised or
     *         contains invalid arguments
     */
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
            default -> throw new HampsterException("invalid instruction");
        };
    }
}
