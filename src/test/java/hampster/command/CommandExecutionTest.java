package hampster.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import hampster.exception.HampsterException;
import hampster.task.Deadline;
import hampster.task.TaskList;
import hampster.task.ToDo;
import hampster.ui.Ui;

class CommandExecutionTest {

    private static final LocalDateTime START = LocalDateTime.of(2026, 9, 15, 10, 0);
    private static final LocalDateTime END = LocalDateTime.of(2026, 9, 15, 11, 0);

    @Test
    void todoCommand_execute_addsTaggedTaskAndReportsIt() throws HampsterException {
        TaskList tasks = new TaskList();
        RecordingUi ui = new RecordingUi();

        new ToDoCommand(new String[] {"todo", "write", "tests", "/tag", "#SCHOOL"})
                .execute(tasks, ui);

        assertEquals(1, tasks.size());
        assertEquals("T|0|write tests|#school", tasks.get(0).saveString());
        assertEquals(
                List.of(
                        "\tExcellent. Another task for my evil little empire.",
                        "\t[T][ ] write tests #school",
                        "\tMy empire now contains 1 tasks."
                ),
                ui.messages
        );
    }

    @Test
    void deadlineCommand_execute_addsDeadline() throws HampsterException {
        TaskList tasks = new TaskList();
        RecordingUi ui = new RecordingUi();

        new DeadlineCommand(new String[] {
            "deadline", "submit", "report", "/by", "15/9/2026", "1730", "/tag", "#WORK"
        }).execute(tasks, ui);

        assertEquals(1, tasks.size());
        assertEquals("D|0|submit report|Sep 15 2026, 05:30 PM|#work", tasks.get(0).saveString());
    }

    @Test
    void eventCommand_execute_addsEvent() throws HampsterException {
        TaskList tasks = new TaskList();
        RecordingUi ui = new RecordingUi();

        new EventCommand(new String[] {
            "event", "team", "meeting", "/from", "15/9/2026", "1000",
            "/to", "15/9/2026", "1100", "/tag", "#TEAM"
        }).execute(tasks, ui);

        assertEquals(1, tasks.size());
        assertEquals(
                "E|0|team meeting|Sep 15 2026, 10:00 AM|Sep 15 2026, 11:00 AM|#team",
                tasks.get(0).saveString()
        );
    }

    @Test
    void markCommand_execute_togglesTaskAndReportsBothStates() throws HampsterException {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("Finish assignment"));
        RecordingUi ui = new RecordingUi();

        MarkCommand command = new MarkCommand(new String[] {"mark", "1"});
        command.execute(tasks, ui);
        command.execute(tasks, ui);

        assertFalse(tasks.get(0).toString().contains("[X]"));
        assertEquals(
                List.of(
                        "\tExcellent. Task 1 has been conquered.",
                        "\t[T][X] Finish assignment",
                        "\tCurses! Task 1 has escaped. Recapture it at once.",
                        "\t[T][ ] Finish assignment"
                ),
                ui.messages
        );
    }

    @Test
    void deleteCommand_execute_removesRequestedTask() throws HampsterException {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("Keep this"));
        tasks.add(new ToDo("Delete this"));
        RecordingUi ui = new RecordingUi();

        new DeleteCommand(new String[] {"delete", "2"}).execute(tasks, ui);

        assertEquals(1, tasks.size());
        assertEquals("T|0|Keep this|", tasks.get(0).saveString());
        assertTrue(ui.messages.get(1).contains("Delete this"));
    }

    @Test
    void listCommand_execute_reportsAllTasksWithOneBasedNumbers() throws HampsterException {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("First"));
        tasks.add(new Deadline(false, "Second", START));
        RecordingUi ui = new RecordingUi();

        new ListCommand(new String[] {"list"}).execute(tasks, ui);

        assertEquals(
                List.of(
                        "\tBehold! The task dossier of my evil little empire:",
                        "\t1. [T][ ] First",
                        "\t2. [D][ ] Second (by: Sep 15 2026, 10:00 AM)"
                ),
                ui.messages
        );
    }

    @Test
    void findCommand_execute_reportsMatchesAndNoMatchMessage() throws HampsterException {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("Read Java book"));
        tasks.add(new ToDo("Buy groceries"));
        RecordingUi ui = new RecordingUi();

        new FindCommand(new String[] {"find", "Java"}).execute(tasks, ui);

        assertEquals(
                List.of(
                        "\tMy spies found these tasks in the dossier:",
                        "\t1. [T][ ] Read Java book"
                ),
                ui.messages
        );

        ui.messages.clear();
        new FindCommand(new String[] {"find", "Python"}).execute(tasks, ui);
        assertEquals(
                List.of(
                        "\tMy spies found these tasks in the dossier:",
                        "\tNothing escaped into the dossier. Try another keyword."
                ),
                ui.messages
        );
    }

    @Test
    void tagAndUntagCommands_updateOnlyTheRequestedTask() throws HampsterException {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("Tagged task"));
        tasks.add(new ToDo(false, "Other task", "#other"));
        RecordingUi ui = new RecordingUi();

        new TagCommand(new String[] {"tag", "1", "#WORK"}).execute(tasks, ui);
        assertEquals("#work", tasks.get(0).getTag());

        new UntagCommand(new String[] {"untag", "1", "#WORK"}).execute(tasks, ui);
        assertEquals("", tasks.get(0).getTag());

        new UntagCommand(new String[] {"untag", "2", "#work"}).execute(tasks, ui);
        assertEquals("#other", tasks.get(1).getTag());
    }

    @Test
    void commands_withMissingTask_failSafely() throws HampsterException {
        TaskList tasks = new TaskList();
        RecordingUi ui = new RecordingUi();

        assertThrows(AssertionError.class, () ->
                new MarkCommand(new String[] {"mark", "1"}).execute(tasks, ui));
        assertThrows(HampsterException.class, () ->
                new DeleteCommand(new String[] {"delete", "1"}).execute(tasks, ui));
        assertThrows(HampsterException.class, () ->
                new TagCommand(new String[] {"tag", "1", "#work"}).execute(tasks, ui));
        assertThrows(HampsterException.class, () ->
                new UntagCommand(new String[] {"untag", "1", "#work"}).execute(tasks, ui));
    }

    @Test
    void byeCommand_execute_shutsDownUi() throws HampsterException {
        RecordingUi ui = new RecordingUi();

        new ByeCommand(new String[] {"bye"}).execute(new TaskList(), ui);

        assertTrue(ui.shutdownCalled);
    }

    private static final class RecordingUi extends Ui {
        private final List<String> messages = new ArrayList<>();
        private boolean shutdownCalled;

        @Override
        public void showMessage(String... messages) {
            this.messages.addAll(Arrays.asList(messages));
        }

        @Override
        public void shutdown() {
            shutdownCalled = true;
        }
    }
}
