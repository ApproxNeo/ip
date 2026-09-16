package hampster.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import hampster.command.ByeCommand;
import hampster.command.DeadlineCommand;
import hampster.command.DeleteCommand;
import hampster.command.EventCommand;
import hampster.command.FindCommand;
import hampster.command.ListCommand;
import hampster.command.MarkCommand;
import hampster.command.TagCommand;
import hampster.command.ToDoCommand;
import hampster.command.UntagCommand;
import hampster.exception.HampsterException;

class CommandParserTest {

    @Test
    void parse_nullOrBlankInput_throwsException() {
        assertThrows(HampsterException.class, () -> CommandParser.parse(null));
        assertThrows(HampsterException.class, () -> CommandParser.parse("   "));
    }

    @Test
    void parse_unknownCommand_throwsException() {
        assertThrows(HampsterException.class, () -> CommandParser.parse("destroy everything"));
    }

    @Test
    void parse_supportedCommands_returnsMatchingCommandTypes() throws HampsterException {
        assertInstanceOf(ByeCommand.class, CommandParser.parse("bye"));
        assertInstanceOf(ListCommand.class, CommandParser.parse("LIST"));
        assertInstanceOf(MarkCommand.class, CommandParser.parse("mark 1"));
        assertInstanceOf(FindCommand.class, CommandParser.parse("find report"));
        assertInstanceOf(DeleteCommand.class, CommandParser.parse("delete 1"));
        assertInstanceOf(ToDoCommand.class, CommandParser.parse("todo write tests"));
        assertInstanceOf(
                DeadlineCommand.class,
                CommandParser.parse("deadline submit report /by 15/9/2026 1730")
        );
        assertInstanceOf(
                EventCommand.class,
                CommandParser.parse("event team meeting /from 15/9/2026 1000 /to 15/9/2026 1100")
        );
        assertInstanceOf(TagCommand.class, CommandParser.parse("tag 1 #work"));
        assertInstanceOf(UntagCommand.class, CommandParser.parse("untag 1 #work"));
    }

    @Test
    void parse_whitespaceAroundInput_acceptsCommand() throws HampsterException {
        assertInstanceOf(ToDoCommand.class, CommandParser.parse("  todo   write   tests  "));
    }

    @Test
    void parse_commandsWithInvalidArity_throwsException() {
        assertThrows(HampsterException.class, () -> CommandParser.parse("bye now"));
        assertThrows(HampsterException.class, () -> CommandParser.parse("list extra"));
        assertThrows(HampsterException.class, () -> CommandParser.parse("find"));
        assertThrows(HampsterException.class, () -> CommandParser.parse("find two words"));
        assertThrows(HampsterException.class, () -> CommandParser.parse("mark"));
        assertThrows(HampsterException.class, () -> CommandParser.parse("delete one two"));
        assertThrows(HampsterException.class, () -> CommandParser.parse("tag 1"));
        assertThrows(HampsterException.class, () -> CommandParser.parse("untag 1 #one #two"));
    }

    @Test
    void parse_invalidTaskNumbers_throwsException() {
        assertThrows(HampsterException.class, () -> CommandParser.parse("mark zero"));
        assertThrows(HampsterException.class, () -> CommandParser.parse("delete 0"));
        assertThrows(HampsterException.class, () -> CommandParser.parse("tag -1 #work"));
        assertThrows(HampsterException.class, () -> CommandParser.parse("untag 1.5 #work"));
    }

    @Test
    void parse_todoWithInvalidDescription_throwsException() {
        assertThrows(HampsterException.class, () -> CommandParser.parse("todo"));
        assertThrows(HampsterException.class, () -> CommandParser.parse("todo invalid|description"));
        assertThrows(HampsterException.class, () -> CommandParser.parse("todo task /tag work"));
        assertThrows(HampsterException.class, () -> CommandParser.parse("todo task /tag #one /tag #two"));
    }

    @Test
    void parse_deadlineWithInvalidInput_throwsException() {
        assertThrows(HampsterException.class, () -> CommandParser.parse("deadline"));
        assertThrows(HampsterException.class, () -> CommandParser.parse("deadline submit report"));
        assertThrows(HampsterException.class, () -> CommandParser.parse("deadline /by 15/9/2026 1730"));
        assertThrows(HampsterException.class, () -> CommandParser.parse("deadline submit|report /by 15/9/2026 1730"));
        assertThrows(HampsterException.class, () -> CommandParser.parse("deadline submit /by not-a-date"));
    }

    @Test
    void parse_eventWithInvalidInput_throwsException() {
        assertThrows(HampsterException.class, () -> CommandParser.parse("event"));
        assertThrows(HampsterException.class, () -> CommandParser.parse("event team meeting"));
        assertThrows(HampsterException.class, () -> CommandParser.parse(
                "event /from 15/9/2026 1000 /to 15/9/2026 1100"));
        assertThrows(HampsterException.class, () -> CommandParser.parse("event team /from 15/9/2026 1000"));
        assertThrows(HampsterException.class, () -> CommandParser.parse(
                "event team|meeting /from 15/9/2026 1000 /to 15/9/2026 1100"));
        assertThrows(HampsterException.class, () -> CommandParser.parse(
                "event team /from 15/9/2026 1100 /to 15/9/2026 1000"));
        assertThrows(HampsterException.class, () -> CommandParser.parse("event team /from bad /to 15/9/2026 1000"));
    }
}
