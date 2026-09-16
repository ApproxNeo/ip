package hampster.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

class TaskListTest {

    @Test
    void find_returnsTasksWhoseDescriptionContainsKeyword() {
        ToDo matchingTask = new ToDo("Read about Java");
        TaskList tasks = new TaskList();
        tasks.add(matchingTask);
        tasks.add(new ToDo("Buy groceries"));
        tasks.add(new ToDo("Review Java tests"));

        TaskList result = tasks.find("Java");

        assertEquals(2, result.size());
        assertSame(matchingTask, result.get(0));
        assertEquals("Review Java tests", result.get(1).description);
    }

    @Test
    void find_withNoMatches_returnsEmptyList() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("Read about Java"));

        assertEquals(0, tasks.find("Python").size());
    }

    @Test
    void find_withNullKeyword_throwsAssertionError() {
        TaskList tasks = new TaskList();

        assertThrows(AssertionError.class, () -> tasks.find(null));
    }

    @Test
    void printTasks_printsEachTaskOnItsOwnLine() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("First"));
        tasks.add(new ToDo(true, "Second"));

        PrintStream originalOutput = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        try {
            tasks.printTasks();
        } finally {
            System.setOut(originalOutput);
        }

        String expected = String.join(
                System.lineSeparator(),
                "[T][ ] First",
                "[T][X] Second"
        ) + System.lineSeparator();
        assertEquals(expected, output.toString(StandardCharsets.UTF_8));
    }
}
